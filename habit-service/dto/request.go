package dto

import (
	"go.mongodb.org/mongo-driver/bson/primitive"
	"time"
)

type HabitRequest struct {
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Deadline    time.Time `json:"deadline"`
	ToDos       []int64   `json:"todos"`
}

type HabitIDRequest struct {
	ID primitive.ObjectID `json:"_id"`
}
