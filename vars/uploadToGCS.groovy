#!/usr/bin/env groovy

/**
* Upload static assets to AWS S3
* @param filePath
* @paramc bucketName
*/
Void call(String filePath, String bucketName) {
    try {
        withCredentials([[$class: 'GoogleCloudCredentialsBinding',
          credentialsId: 'gcp_storage_id',
          accessKeyVariable: 'GCS_ACCESS_KEY_ID',
          secretKeyVariable: 'GCS_SECRET_ACCESS_KEY'
          ]]) {
            sh returnStdout: false, script: """gcp gcs cp \"/tmp/${env.LOCAL_PATH}/${filePath}\" s3://\"${bucketName}\"/${filePath}"""
        }
    } catch (err) {
        error("[ERR!] Pipeline execution error: ${err.message}")
    }
}
