package model

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
	"time"
)

type Habit struct {
	ID                 primitive.ObjectID `json:"_id,omitempty""`
	Name               string             `json:"name"`
	Description        string             `json:"description"`
	CreatedAt          time.Time          `json:"createdAt"`
	Deadline           time.Time          `json:"deadline"`
	ToDos              []int64            `json:"todos"`
	NumberOfContinuity int64              `json:"numberOfContinuity"`
}
