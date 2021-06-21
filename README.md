<p align="center">
  <a target="_blank"><img src="https://sendgrid.com/wp-content/themes/sgdotcom/pages/resource/brand/2016/SendGrid-Logomark.png" width="120" alt="Angular Logo" /></a>
<a target="_blank"><img src="https://spring.io/images/projects/spring-edf462fec682b9d48cf628eaf9e19521.svg" width="100" alt="Spring Logo" /></a>
</p>


[![GitHub license](https://img.shields.io/github/license/mashape/apistatus.svg)](https://github.com/fedexu/love-article/blob/master/LICENSE)
[![sonar qube](https://sonarcloud.io/api/project_badges/measure?project=fedexu_mail-sender&metric=alert_status)](https://sonarcloud.io/dashboard?id=fedexu_mail-sender)

# MailSender
This is a simple java integration service for SendGrid API.
It's used mainly as a Hub mail service for project deploy on virtual instances or raspberry.

## Installation
Create a file secret.yaml on the java/resources folder and put the API key.

<code>
SENDGRID_API_KEY: myKey
</code>

## Api Table
Table of api:

Path | Method | Request param | Request body | 
--- | --- | --- | --- |
/api/mail | POST | n/a | ```{ "body": "body", "email": [ "test@test.com" ], "name": "Name" }``` | 


