package service

import (
	"context"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
	"habit-service/db"
	"habit-service/dto"
	"habit-service/model"
	"habit-service/utils"
	"log"
	"time"
)

var mongoDBConn = db.GetMongoDBConn()
var collectionName = utils.GetEnv("COLLECTION_NAME")
var dbName = utils.GetEnv("DB_NAME")
var collection = mongoDBConn.GetCollection(dbName, collectionName)

func InsertHabit(habit dto.HabitRequest) (*mongo.InsertOneResult, error) {
	doc := bson.D{
		{"name", habit.Name},
		{"description", habit.Description},
		{"createdAt", time.Now()},
		{"deadline", habit.Deadline},
		{"todos", habit.ToDos},
		{"numberOfContinuity", 0},
	}

	result, err := collection.InsertOne(context.TODO(), doc)
	if err != nil {
		log.Println("Could not to insert new habit!")
		return nil, err
	}
	log.Println("Habit is added!")
	return result, nil
}
func GetHabit(habitID dto.HabitIDRequest) (model.Habit, error) {
	filter := bson.D{{"_id", habitID.ID}}
	opts := options.FindOne()

	var result model.Habit
	err := collection.FindOne(context.TODO(), filter, opts).Decode(&result)

	if err != nil {
		log.Println("Could not to find!")
		return model.Habit{}, err
	}
	result.ID = habitID.ID
	log.Printf("Habit is founded %v", result)
	return result, nil
}
