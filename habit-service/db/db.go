package db

import (
	"context"
	"fmt"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"habit-service/utils"
	"sync"
)

var lock = &sync.Mutex{}

type MongoDBConn struct {
	Client *mongo.Client
}

var mongoDBConn *MongoDBConn

func init() {
	dbUsername := utils.GetEnv("DB_USERNAME")
	dbPassword := utils.GetEnv("DB_PASSWORD")
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
