# 🌐 포팅 매뉴얼

# 버전 정보

## 백

- Java - `openjdk 17.0.9 correto`
- Spring - `3.2.3 Gradle, Jar`
- Tomcat - `10.1.19`

## 프론트

- npm - `10.1.0`
- NodeJS - `20.11.0`
- React - `18.2.0`
- Typescript - `5.2.2`
- package.json
  ```json
  {
    "name": "frontend",
    "private": true,
    "version": "0.0.0",
    "type": "module",
    "scripts": {
      "dev": "vite",
      "build": "tsc && vite build",
      "lint": "eslint . --ext ts,tsx --report-unused-disable-directives --max-warnings 0",
      "preview": "vite preview",
      "prettier": "prettier --write ."
    },
    "dependencies": {
      "axios": "^1.6.8",
      "event-source-polyfill": "^1.0.31",
      "react": "^18.2.0",
      "react-dom": "^18.2.0",
      "react-router-dom": "^6.22.2",
      "styled-components": "^6.1.8",
      "zustand": "^4.5.2"
    },
    "devDependencies": {
      "@types/event-source-polyfill": "^1.0.5",
      "@types/react": "^18.2.56",
      "@types/react-dom": "^18.2.19",
      "@types/styled-components": "^5.1.34",
      "@typescript-eslint/eslint-plugin": "^7.0.2",
      "@typescript-eslint/parser": "^7.0.2",
      "@vitejs/plugin-react": "^4.2.1",
      "eslint": "^8.57.0",
      "eslint-config-prettier": "^9.1.0",
      "eslint-plugin-prettier": "^5.1.3",
      "eslint-plugin-react-hooks": "^4.6.0",
      "eslint-plugin-react-refresh": "^0.4.5",
      "prettier": "^3.2.5",
      "typescript": "^5.2.2",
      "vite": "^5.1.4"
    }
  }
  ```

## AI
- Python 실행환경: Colab 실행 환경을 사용
- 최신 버전을 유지

- requirements.txt
  ```
  pip install --upgrade pip
  pip install diffusers
  pip install boto3
  pip install redis
  pip install python-dotenv
  ```

  

## 기타

- EC2 ubuntu - `20.04.6 LTS`
- Docker - `25.0.4`
- Nginx - `1.25.3`
- MySQL - `8.0.36`
- MySQLWorkbench - `8.0.21`
- Redis - `7.2.4 LTS`
- Sonarqube - `4.2.0.3129`

# 포트 정보

```jsx
- 80 : 프록시 서버 → 443
- 443 : 프록시 서버(SSL)

- 3001 : 프론트 서버(배포용)
- 3002 : 백 서버(개발용)  
- 3003 : 백 서버(배포용)
- 3100 : 젠킨스 서버
- 6379: Redis

// Mysql은 docker network로만 관리
// Sonarqube는 Ssafy 제공
```


# 변수 및 보안 정보

- 백
  ```yaml
  spring:
    datasource:
      url: jdbc:mysql://db-deploy:3306/flowermari
      username: {USERNAME}
      password: {PASSWORD}
      driver-class-name: com.mysql.cj.jdbc.Driver
      
    jpa:
      hibernate:
        ddl-auto: update
      properties:
        hibernate:
          format_sql: true
          dialect: org.hibernate.dialect.MySQLDialect
      open-in-view : false
    
    data:
      redis:
        host: redis
        port: 6379
        password: {REDIS_PASSWORD}
    
    jwt:
      header: Authorization
      secret: {YOUR_SECRET_SSL_ENCRYPTION_CREATED_BY_openssl rand -base64 60}
      access-token-validity-in-seconds: 7200
      refresh-token-validity-in-seconds: 86400

    security:
      oauth2:
        client:
          provider:
            kakao:
              authorization-uri: https://kauth.kakao.com/oauth/authorize
              token-uri: https://kauth.kakao.com/oauth/token
              user-info-uri: https://kapi.kakao.com/v2/user/me
              user-name-attribute: id
          registration:
            kakao:
              client-id: {KAKAO_CLIENT_ID}
              client-secret: {KAKAO_CLIENT_SECRET}
              client-authentication-method: client_secret_post
              redirect-uri: {YOUR_DOMAIN}/auth/kakao
              authorization-grant-type: authorization_code
              client-name: kakao
              scope:
                - profile_nickname
                - profile_image

            password-salt: {YOUR_PASSWORD_SALT} 

  logging.level:
    org.hibernate.SQL: debug

  chatgpt:
    api:
      endpoint: https://api.openai.com/v1/chat/completions
      key: {CHAT_GPT_KEY}
      model : gpt-3.5-turbo-0125
      
  cloud:
    aws:
      s3:
        bucket: {YOUR_BUCKET_NAME}
        region:
          static: ap-northeast-2
      stack.auto: false
      credentials:
        access-key: {YOUR_ACCESS_KEY}
        secret-key: {YOUR_SECRET_KEY}

  aes:
    secret-key: {YOUR_AES128_ENCRYPTION_SECRET_KEY} 
  ```


- 프론트
  ```bash
  VITE_API_BASE_URL={YOUR_DOMAIN_NAME}/api
  VITE_JWT_ACCESS_EXPIRE_TIME=7200
  VITE_JWT_REFRESH_EXPIRE_TIME=86400
  VITE_KAKAO_CLIENT_ID={KAKAO_PROJECT_RESTAPI_KEY}
  VITE_KAKAO_REDIRECT_URI={YOUR_DOMAIN_NAME}/auth/kakao
  VITE_KAKAO_MAP_KEY={KAKAO_PROJECT_JAVASCRIPT_KEY}
  ```

- AI
  ```bash
  SD_MODEL_NAME = "model.safetensors"

  REDIS_HOST = {YOUR REDIS SERVER IP ADDRESS or DOMAIN NAME}
  REDIS_PORT = 6379
  REDIS_PASSWORD = {YOUR REDIS PASSWORD}

  AWS_ACCESS_KEY = {YOUR AWS ACCESS KEY}
  AWS_SECRET_KEY = {YOUR AWS SECRET KEY}
  AWS_BUCKET_NAME = {YOUR AWS BUCKET NAME}
  AWS_REGION_NAME = {YOUR AWS REGION NAME}
  ```

# 방화벽 정보

- `ufw status`를 통해 나오는 정보는 다음과 같다.
  ```jsx
  To                         Action      From
  --                         ------      ----
  22                         ALLOW       Anywhere
  8989                       ALLOW       Anywhere
  443                        ALLOW       Anywhere
  80                         ALLOW       Anywhere
  3001                       ALLOW       Anywhere
  3201                       ALLOW       Anywhere
  3200                       ALLOW       Anywhere
  3002                       ALLOW       Anywhere
  6379                       ALLOW       Anywhere
  22 (v6)                    ALLOW       Anywhere (v6)
  8989 (v6)                  ALLOW       Anywhere (v6)
  443 (v6)                   ALLOW       Anywhere (v6)
  80 (v6)                    ALLOW       Anywhere (v6)
  3001 (v6)                  ALLOW       Anywhere (v6)
  3201 (v6)                  ALLOW       Anywhere (v6)
  3200 (v6)                  ALLOW       Anywhere (v6)
  3002 (v6)                  ALLOW       Anywhere (v6)
  6379 (v6)                  ALLOW       Anywhere (v6)
  ```

# 서버 환경 구축 방법

- 할당 받은 ec2에서 안전한 환경 구축을 위해 도커를 설치하여 프록시 서버인 Nginx를 제외한 모든 프로그램을 도커 컨테이너로 관리한다.

- 서버 구축에 관련한 docker-compose.yml, Dockerfile과 .env, application.yml등의 보안 정보는 개인 레포지토리`{YOUR PRIVATE GIT REPOSITORY}`에서 관리한다.

- 젠킨스를 통해 배포를 자동화하여 관리하였고 `/var/jenkins_home` 에 settings 디렉토리를 만들어 세팅 파일을 관리하였기에 이후의 스크립트에서 해당 경로가 등장할 수 있다.
- 젠킨스 디렉토리 구조
  ```
  /var/jenkins_home/settings
  ├── front/  : Dockerfile 
  │   ├── static/ : assets, index.html, vite.svg
  │   └── conf.d/ : default.conf
  ├── back-dev/ : Dockerfile, application.yml, maryflower-0.0.1-SNAPSHOT.jar
  ├── back-deploy/ : Dockerfile, application.yml, maryflower-0.0.1-SNAPSHOT.jar
  ├── jenkins/ : Dockerfile
  └── docker-compose.yml
  ```


# 프로젝트 세팅


- 도커 설치
  ```bash
  apt install -y apt-transport-https ca-certificates curl gnupg-agent software-properties-common
  curl -fsSL https://download.docker.com/linux/ubuntu/gpg | apt-key add -
  add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"

  sudo add-apt-repository --remove ppa:certbot/certbot


  apt update
  apt install -y docker-ce docker-ce-cli containerd.io docker-compose docker-compose-plugin


  systemctl status docker
  ```

- Nginx 설치 및 세팅
  - Nginx를 프록시 서버로 활용하여 배포를 진행한다.
  - Nginx 설치
    ```bash
    sudo touch /etc/apt/sources.list.d/nginx.list
    echo "deb http://nginx.org/packages/ubuntu/ bionic nginx" | sudo tee -a /etc/apt/sources.list.d/nginx.list
    echo "deb-src http://nginx.org/packages/ubuntu/ bionic nginx"| sudo tee -a /etc/apt/sources.list.d/nginx.list

    # 인증 키 등록
    wget http://nginx.org/keys/nginx_signing.key
    sudo apt-key add nginx_signing.key

    # 저장소 업데이트
    sudo apt-get update

    # nginx 설치
    sudo apt-get install nginx
    ```

  - 80번 포트로 들어온 요청은 ssl인증을 받기 위해 443 포트로 리디렉션 된 이후 각 서버로 프록시 패스된다.

  - SSL 인증은 여러가지 방법이 있고, 본 프로젝트는 ZeroSSL로 진행되었다
    1. ZeroSSL
      - [공식 사이트](https://help.zerossl.com/hc/en-us/articles/360058295894-Installing-SSL-Certificate-on-NGINX)
      - 해당 프로젝트에서는 /etc/zerossl 디렉토리를 만들어서 넣어주었다.

    2. Letsencrypt
      - 이 링크에서 설정 방법을 보다 자세히 볼 수 있다. [https://docs.openvidu.io/en/2.29.0/deployment/ce/on-premises/](https://docs.openvidu.io/en/2.29.0/deployment/ce/on-premises/)


  - 백엔드는 개발용과 배포용 서버를 따로 띄워 관리하며 이에 따라 Nginx에서 업스트림으로 이름을 설정한다.
  - default.conf

    ```bash
    upstream ssafy{
    server j10a704.p.ssafy.io:3002;
    }

    upstream ssafy-deploy{
    server j10a704.p.ssafy.io:3003;
    }


    server {
      listen 443 ssl;
      listen [::]:443 ssl;
      ssl_certificate /etc/zerossl/certificate.crt;
      ssl_certificate_key /etc/zerossl/private.key;

      server_name j10a704.p.ssafy.io;

      access_log /var/log/nginx/nginx.vhost.access.log;
      error_log /var/log/nginx/nginx.vhost.error.log;

      location / {
            proxy_pass http://j10a704.p.ssafy.io:3001;
      }

      location /api2 {
            rewrite ^/api2/(.*)$ /$1 break;
            proxy_pass http://ssafy;
            proxy_pass_request_headers on;
            proxy_read_timeout 600s;
      }

      location /api {
            rewrite ^/api/(.*)$ /$1 break;
            proxy_pass http://ssafy-deploy;
            proxy_pass_request_headers on;
            proxy_read_timeout 600s;
      }


    }


    server {
              listen 80; listen [::]:80;
              server_name j10a704.p.ssafy.io;
              return 301 https://$host$request_uri;
    }

    ```

* Dockerfile
  * front
    ```docker
    FROM nginx:1.25.3

    EXPOSE 3001

    CMD ["nginx", "-g", "daemon off;"]
    ```
  * Back
    ```docker
    FROM openjdk:17

    USER root

    WORKDIR /

    COPY ./*.jar /app.jar
    COPY application.yml .

    EXPOSE 8080

    CMD ["java",  "-jar", "/app.jar"]
    ```
  * Jenkins
    ```docker
    FROM jenkins/jenkins:lts

    ENV JENKINS_HOME /var/jenkins_home
    ENV CASC_JENKINS_CONFIG /var/jenkins_home/casc_configs

    USER root
    RUN apt-get update && \
        apt-get -y install apt-transport-https ca-certificates curl gnupg2 software-properties-common && \
        curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg > /tmp/dkey && \
        apt-key add /tmp/dkey && \
        add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") $(lsb_release -cs) stable" && \
        apt-get update && \
        apt-get -y install docker-ce docker-ce-cli containerd.io docker-compose docker-compose-plugin

    RUN echo "자바 설치" && sleep 2 && \
        apt-get install -y openjdk-17-jdk

    RUN groupadd -f docker
    RUN usermod -aG docker jenkins

    USER jenkins
    ```

* docker-compose.yml

  ```yaml
  version: '3.1'

  services:

    jenkins:
      container_name: jenkins
      networks:
        - flowermari
      build:
        context: ./jenkins
        dockerfile: Dockerfile
      image: ${JENKINS_IMAGE_NAME}
      restart: always
      ports:
        - "3100:8080"
        - "50000:50000"
      volumes:
        - /var/jenkins_home:/var/jenkins_home
        - /var/run/docker.sock:/var/run/docker.sock
      user: jenkins

    db-dev:
      container_name: db-dev
      networks:
        - flowermari
      image: mysql:8.0.36
      restart: always
      healthcheck:
        test: /bin/sh -c "mysqladmin ping -h db-deploy -u flowermari -pflowermari"
        interval: 20s
        timeout: 5s
        retries: 5
      environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: flowermari
        MYSQL_USER: flowermari
        MYSQL_PASSWORD: flowermari
      volumes:
        - data_volume:/var/lib/mysql

    db-deploy:
      container_name: db-deploy
      networks:
        - flowermari
      image: mysql:8.0.36
      restart: always
      healthcheck:
        test: /bin/sh -c "mysqladmin ping -h db-deploy -u flowermari -pflowermari"
        interval: 20s
        timeout: 5s
        retries: 5
      environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: flowermari
        MYSQL_USER: flowermari
        MYSQL_PASSWORD: flowermari
      volumes:
        - data_volume_deploy:/var/lib/mysql

    redis:
      container_name: redis
      hostname: redis
      networks:
        - flowermari
      image: redis
      restart: always
      healthcheck:
        test: /bin/sh -c "redis-cli -h redis -a ${REDIS_PASSWORD} ping"
        interval: 20s
        timeout: 5s
        retries: 5
      command: redis-server --requirepass ${REDIS_PASSWORD} --port 6379
      ports:
      - "6379:6379"

    back-dev:
      container_name: back-dev
      build:
        context: ./back-dev
        dockerfile: Dockerfile
      image: ${BACK_DEV_IMAGE_NAME}:${IMAGE_TAG}
      networks:
        - flowermari
      restart: always
      healthcheck:
        test: "curl --fail --silent back-dev:8080/actuator/health | grep UP || exit 1"
        interval: 20s
        timeout: 5s
        retries: 5
      ports:
        - "3002:8080"
      depends_on:
        - db-dev
        - redis
    
    back-deploy:
      container_name: back-deploy
      build:
        context: ./back-deploy
        dockerfile: Dockerfile
      image: ${BACK_DEPLOY_IMAGE_NAME}:${IMAGE_TAG}
      networks:
        - flowermari
      restart: always
      healthcheck:
        test: "curl --fail --silent back-deploy:8080/actuator/health | grep UP || exit 1"
        interval: 20s
        timeout: 5s
        retries: 5
      ports:
        - "3003:8080"
      depends_on:
        - db-deploy
        - redis

    front:
      container_name: front
      networks:
        - flowermari
      build:
        context: ./front
        dockerfile: Dockerfile
      image: ${FRONT_IMAGE_NAME}
      restart: always
      ports:
        - "3001:3001"
      volumes:
        - ./front/static:/usr/share/nginx/html
        - ./front/conf.d:/etc/nginx/conf.d
      depends_on:
        - back-dev
        - back-deploy

  volumes:
    data_volume:
      external: true
    data_volume_deploy:
      external: true

  networks:
    flowermari:
      external: true

  ```
- .env
  ```
  JENKINS_IMAGE_NAME=
  BACK_DEV_IMAGE_NAME=
  BACK_DEPLOY_IMAGE_NAME=
  FRONT_IMAGE_NAME=
  IMAGE_TAG=
  REDIS_PASSWORD=
  ```

# Docker network 정보
* 도커 네트워크 생성

  ```bash
  docker network create flowermari
  docker network inspect flowermari
  ```

* 네트워크 정보
  ```jsx
  - front : 프론트 서버
  - back-dev : 백 서버 (개발용)
  - back-deploy : 백 서버 (배포용)
  - db-dev : MySQL (개발 DB) 
  - db-deploy : MySQL (배포 DB)
  - jenkins : Jenkins
  - redis : Redis
  ```

# 개별 컨테이너 배포시

- 각각의 빌드 및 실행은 각 컨테이너 내에서 실행되어야만 한다.

- Back
  ```bash
  gradlew clean bootJar
  java -jar comeet-0.0.1-SNAPSHOT.jar
  ```
- Front
  - nginx를 띄워서 웹서버로 사용하였다.
  - default.conf
    ```
    server {
    listen 3001;
    server_name j10a704.p.ssafy.io;

    access_log /var/log/nginx/nginx.vhost.access.log;
    error_log /var/log/nginx/nginx.vhost.error.log;

    location / {
    root /usr/share/nginx/html;
    index index.html;
    try_files $uri /index.html;
        }
    }
    ```
  - Build
    ```bash
    npm i pnpm
    pnpm install -f && CI=false pnpm run build
    ```

## Kakao OAuth 사용법

- kakao developer에서 application 추가, Application name 설정
- REST API 키 application.yml에 등록
- 제품설정 → 카카오 로그인에서 Redirect URI 설정 ( {DOMAIN_NAME}/auth/kakao )


```yaml
spring:
  security:
      oauth2:
        client:
          provider:
            kakao:
              authorization-uri: https://kauth.kakao.com/oauth/authorize
              token-uri: https://kauth.kakao.com/oauth/token
              user-info-uri: https://kapi.kakao.com/v2/user/me
              user-name-attribute: id
          registration:
            kakao:
              client-id: {KAKAO_CLIENT_ID}
              client-secret: {KAKAO_CLIENT_SECRET}
              client-authentication-method: client_secret_post
              redirect-uri: {YOUR_DOMAIN}/auth/kakao
              authorization-grant-type: authorization_code
              client-name: kakao
              scope:
                - profile_nickname
                - profile_image
```


# AI 서버 구축 방법

- 구글 드라이브에 8G 의 공간을 확보한다.

- ai_server 폴더를 구글 드라이브에 업로드한다. (하위 파일까지 전부, 압축하지 않은 상태로)

- ai_server 폴더 내부로 진입한다.

- .env 파일을 만들어, 구글 드라이브의 ai_server 폴더에 업로드한다.

    ```bash
  SD_MODEL_NAME = "model.safetensors"

  REDIS_HOST = {YOUR REDIS SERVER IP ADDRESS or DOMAIN NAME}
  REDIS_PORT = 6379
  REDIS_PASSWORD = {YOUR REDIS PASSWORD}

  AWS_ACCESS_KEY = {YOUR AWS ACCESS KEY}
  AWS_SECRET_KEY = {YOUR AWS SECRET KEY}
  AWS_BUCKET_NAME = {YOUR AWS BUCKET NAME}
  AWS_REGION_NAME = {YOUR AWS REGION NAME}
  ```

- SDXL 모델의 이름을 `model.safetensors` 로 바꾼 다음, ai_server 폴더에 업로드한다.
    - 훈련 완료된 모델의 크기는 7.11G이므로, 담당자 한태희가 오프라인에서 별도 보관한다. 필요시 모델 파일을 요청하여 전달받는다.
    - 만약 한태희와 연락이 닿지 않거나 모델 파일이 유실될 경우, 기본 SDXL 모델을 사용하는 것을 고려한다. (https://huggingface.co/stabilityai/stable-diffusion-xl-base-1.0/resolve/main/sd_xl_base_1.0.safetensors)

- ai_server_colab.ipynb 파일을 Google Colab에서 실행한다.

- Google Colab에서 런타임 - 런타임 유형 변경에 들어가, GPU 종류를 T4로 변경한다.

- 런타임 - 모두 실행을 클릭하여 서버를 실행한다.

- 정상적으로 실행될 경우, 마지막 쉘에 아래의 로그가 출력된다.

```
redis 연결상태 True
파이프라인 준비중..

...

파이프라인 준비 완료
```


- 만약 마지막 쉘이 ^C 라는 문자로 종료될 경우, 램이 부족하여 꺼진 것이므로, 서버 실행을 다시 시도해본다. 문제가 계속 발생한다면, 코랩 프로 (월 1만원) 을 결제한 다음, 런타임을 고용량 RAM, GPU T4로 설정하여 실행해본다.