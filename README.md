# webservice

CSYE6225 cloud computation

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

aws cloudformation create-stack --stack-name tst5 --template-body file://6225-infra.yaml --capabilities CAPABILITY_NAMED_IAM --parameters ParameterKey=CloudImageID,ParameterValue=ami-06c3981b43956c5f7

aws cloudformation create-stack --stack-name DBtest --template-body file://6225-infra.yaml --capabilities CAPABILITY_NAMED_IAM --parameters ParameterKey=CloudImageID,ParameterValue=ami-03c4774f0bf14f58d

AKIA354HZBXASQD4NC4V
4HmP8/LARlfTjYhcIlP+4Umz/l3a1mGC47YSz5kD

aws cloudformation create-stack --stack-name testdevDB --template-body file://6225-infra.yaml --capabilities CAPABILITY_NAMED_IAM --parameters ParameterKey=CloudImageID,ParameterValue=ami-0d7c323460517fec8 ParameterKey=BucketName,ParameterValue=prod.domain.tld ParameterKey=AccessKeyId,ParameterValue=AKIA354HZBXASQD4NC4V ParameterKey=AccessKey,ParameterValue=4HmP8/LARlfTjYhcIlP+4Umz/l3a1mGC47YSz5kD
