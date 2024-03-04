pipeline {
    agent any
    tools {
        maven 'maven-3.9.6'
    }
    stages {
        stage('Build'){
            steps {
                dir('spring boot/commons'){
                  sh 'mvn clean install'
                }
            }
        }
        stage('Test'){
            steps {
                dir('spring boot/commons'){
                  sh 'mvn test'
                }
            }
        }
    }
}