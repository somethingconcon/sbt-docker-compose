package com.tapad.docker

import sbt._

object DockerComposeKeys extends DockerComposeKeysLocal

trait DockerComposeKeysLocal {
  val composeFile = settingKey[String]("Specify the full path to the Compose File to use to create your test instance. It defaults to docker-compose.yml in your resources folder.")
  val composeServiceName = settingKey[String]("The name of the service in the Docker Compose file being tested. This setting prevents the service image from being pull down from the Docker Registry. This defaults to the Project name.")
  val composeNoBuild = settingKey[Boolean]("True if a Docker Compose file is to be started without building any images and only using ones that already exist in the Docker Registry. This defaults to False.")
  val composeRemoveContainersOnShutdown = settingKey[Boolean]("True if a Docker Compose should remove containers when shutting down the compose instance. This defaults to True.")
  val composeRemoveTempFileOnShutdown = settingKey[Boolean]("True if a Docker Compose should remove the post Custom Tag processed Compose File on shutdown. This defaults to True.")
  val composeContainerStartTimeoutSeconds = settingKey[Int]("The amount of time in seconds to wait for the containers in a Docker Compose instance to start. Defaults to 500 seconds.")
  val dockerMachineName = settingKey[String]("If running on OSX the name of the Docker Machine Virtual machine being used. If not overridden it is set to 'default'")
  val dockerImageCreationPlugin = settingKey[DockerImagePluginType.Value]("Specifies the sbt plugin being used Docker image creation. This defaults to SbtDocker for the 'sbt-docker' plugin but can also be set to NativePackager for the 'sbt-native-packager' plugin.")
  val testTagsToExecute = settingKey[String]("Set of ScalaTest Tags to execute when dockerComposeTest is run. Separate multiple tags by a comma. It defaults to executing all tests.")
  val testCasesJar = settingKey[String]("The path to the Jar file containing the tests to execute. This defaults to the Jar file with the tests from the current sbt project.")
  val testDependenciesClasspath = taskKey[String]("The path to all managed and unmanaged Test dependencies. This path needs to include the ScalaTest Jar for the tests to execute. This defaults to all managedClasspath and unmanagedClasspath in the Test Scope.")
  val runningInstances = AttributeKey[List[RunningInstanceInfo]]("For Internal Use: Contains information on the set of running Docker Compose instances.")
}

object DockerImagePluginType extends Enumeration {
  type DockerImagePluginType = Value
  val SbtDocker, NativePackager = Value
}