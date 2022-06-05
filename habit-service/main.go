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
	fmt.Println("Starting server on port 8080")
	log.Fatal(http.ListenAndServe(":8080", router))
}
