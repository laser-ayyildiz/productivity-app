package utils

import (
	"fmt"
	"github.com/spf13/viper"
)

func GetEnv(variableName string) string {
	viper.SetConfigFile(".env")
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
