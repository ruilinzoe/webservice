# web service s

CSYE 6225 cloud computation

# prerequisites

For building and running the application you need:

JDK 1.8 Maven 3 Springboot

# Clone the repository through ssh

git clone git@github.com:Ruilin-Zoe-6225/webservice.git

# change directory to The file webservice under the root folder

cd desktop/CSYE6225/webservice

# Running the application locally

./mvnw spring-boot:run

# Deploying the application, check the localhost at

http://localhost:8080/healthz

# webservice

# Assignment 5:

packer build ami.json

aws cloudformation create-stack --stack-name XXX --template-body file://6225-infra.yaml --parameters ParameterKey=CloudImageID,ParameterValue=ami-XXX

AKIA354HZBXASQD4NC4V
4HmP8/LARlfTjYhcIlP+4Umz/l3a1mGC47YSz5kD

aws cloudformation create-stack --stack-name ec --template-body file://6225-infra.yaml --capabilities CAPABILITY_NAMED_IAM --parameters ParameterKey=CloudImageID,ParameterValue=ami-03774ede6479de6bf ParameterKey=BucketName,ParameterValue=prod.domain.tld ParameterKey=AccessKeyId,ParameterValue=AKIAYLSEFMVHOG2IVWVU ParameterKey=AccessKey,ParameterValue=U570/5HkKV4mqrIvyzq8lfEXQ6uYi/ULhDqKOKil

export AWS_PROFILE=demo
export AWS_REGION=us-west-2

aws cloudformation create-stack --stack-name CICD --template-body file://cicd.yml --capabilities CAPABILITY_NAMED_IAM --parameters ParameterKey=BucketName,ParameterValue=prod.domain.tld

aws cloudformation create-stack --stack-name dynab --template-body file://cicd.yml --capabilities CAPABILITY_NAMED_IAM
