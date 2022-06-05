package main

import (
	"fmt"
	"habit-service/router"
	"log"
	"net/http"
)

func main() {
	fmt.Println("Hello, World!")
	router := router.Router()
	fmt.Println("Starting server on port 8000")
	log.Fatal(http.ListenAndServe(":8000", router))
}
