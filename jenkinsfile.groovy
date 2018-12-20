pipeline {
 	agent any
 	stages {
 	stage('BUILD') {
 	steps {
 	echo 'Build start...'
 	sleep 11
 	echo 'Build finish...'
 	}
 	}
 	stage('TEST') {
 	steps {
 	echo 'Test start...'
 	sleep 11
 	echo 'Test finish...'
 	}
 	}
 	stage('DEPLOYEE') {
 	steps {
 	echo 'Deployee start...'
 	sleep 11
 	echo 'Deployee finish...'
 	}
 	}
 	}
 	post { 
 	always { 
 	echo 'Status:Success'
 	}
 	}
 	}
 
 
