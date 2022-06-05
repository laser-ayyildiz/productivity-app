package db

import (
	"context"
	"fmt"
	"github.com/spf13/viper"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"sync"
)

var lock = &sync.Mutex{}

type MongoDBConn struct {
	Client *mongo.Client
}

var mongoDBConn *MongoDBConn

func getDBEnv(variableName string) string {
	viper.SetConfigFile("../.env")
	err := viper.ReadInConfig()
	if err != nil {
		fmt.Println(err)
	}

	value, ok := viper.Get(variableName).(string)

	if !ok {
		fmt.Println("There is no value!")
		return ""
	} else {
		return value
	}
}

func init() {
	dbUsername := getDBEnv("DB_USERNAME")
	dbPassword := getDBEnv("DB_PASSWORD")
	connectionString := "mongodb+srv://" + dbUsername + ":" + dbPassword + "@habitcluster.lb261.mongodb.net/?retryWrites=true&w=majority"
	clientOptions := options.Client().ApplyURI(connectionString)

	client, err := mongo.Connect(context.TODO(), clientOptions)
	if err != nil {
		fmt.Println(err)
	}

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		fmt.Println(err)
	}

	mongoDBConn = &MongoDBConn{Client: client}
}

func GetMongoDBConn() *MongoDBConn {
	if mongoDBConn == nil {
		lock.Lock()
		defer lock.Unlock()
		if mongoDBConn == nil {
			fmt.Println("Creating to database...")
		} else {
			fmt.Println("Database is already connected!")
		}
	} else {
		fmt.Println("Database is already created!")
	}
	return mongoDBConn
}

func (MongoDBConn *MongoDBConn) GetCollection(dbName string, collName string) *mongo.Collection {
	if MongoDBConn != nil {
		return MongoDBConn.Client.Database(dbName).Collection(collName)
	} else {
		fmt.Println("MongoDBConn is nil!")
		return nil
	}
}
