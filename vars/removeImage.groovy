#!/usr/bin/env groovy

/**
 * Delete docker image on current host
 * @param imageName
 * @param imageTag
 */
Void call(String imageName, String imageTag, String containerHost='worker') {
    repository = 'quay.io/checkmateadmin'
    withDockerHost(containerHost) {
        try {
            println "---> removing image '${repository}/${imageName}/${imageTag}"
            sh returnStdout: false, script: "docker rmi ${repository}/${imageName}:${imageTag}"
        } catch (err) {
            error("[ERR!] Pipeline step removeContainerImage() execution error: ${err.message}")
        }
    }
 }
