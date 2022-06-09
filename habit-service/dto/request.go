package dto

import "time"

type HabitRequest struct {
	Name        string    `json:"name"`
	Description string    `json:"description"`
	Deadline    time.Time `json:"deadline"`
	ToDos       []int64   `json:"todos"`
}
