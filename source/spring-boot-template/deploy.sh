#!/bin/bash

# kubectl get namespace ${NAMESPACE} || kubectl create namespace ${NAMESPACE}

# KUBECTL_VERSION=1.25.8
# curl -LO https://storage.googleapis.com/kubernetes-release/release/v${KUBECTL_VERSION}/bin/linux/amd64/kubectl \
# && chmod +x kubectl
# ./kubectl version --client

# stage=${ENVIRONMENT}
# STAGESTRING=$stage
# if [[ "$STAGESTRING" == "dev" ]]
# then
#   HELM_TIMEOUT=15m0s
# elif [[ "$STAGESTRING" == "stage" ]]
# then
#   HELM_TIMEOUT=15m0s
# else
#   HELM_TIMEOUT=20m0s
# fi
# echo "Helm Timeout for ${STAGESTRING} stage: ${HELM_TIMEOUT}"

# HELM_VERSION=3.10.3
# curl -LO https://get.helm.sh/helm-v${HELM_VERSION}-linux-amd64.tar.gz \
# && tar xf helm-v${HELM_VERSION}-linux-amd64.tar.gz \
# && rm -f helm-v${HELM_VERSION}-linux-amd64.tar.gz \
# && mv linux-amd64/helm helm \
# && chmod +x helm \
# && rm -rf linux-amd64
# helm version --client

# echo "appVersion: ${APP_VERSION}" >> Source/helm/app/Chart.yaml && cat Source/helm/app/Chart.yaml

# helm repo update
# helm upgrade ${APP_NAME} helm \
# --wait \
# --install \
# --timeout ${HELM_TIMEOUT} \
# --namespace ${NAMESPACE} \
# --values helm/stages/${REGION}.${ENVIRONMENT}/values.yaml \
# --set image.registry=${APP_REGISTRY_URL} \
# --set image.username=${APP_REGISTRY_USERNAME} \
# --set image.password=${APP_REGISTRY_PASSWORD} \
# --set image.repository=${APP_NAME} \
# --set image.tag=${APP_VERSION} \
# || exit 4

helm upgrade \
--install spring-boot-template ./helm \
--namespace test-namespace \
--values ./helm/stages/dev/values.yaml \
--set image.registry=${APP_REGISTRY_URL} \
--set image.username=${APP_REGISTRY_USERNAME} \
--set image.password=${APP_REGISTRY_PASSWORD} \
--set image.repository=spring-boot-starter \
--set image.tag=latest \
|| exit 4