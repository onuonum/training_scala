# デプロイ
## jarの置き場作成
```
aws s3 mb s3://tetetest
```
## swaggerをデプロイ
```
aws s3 cp swagger.yaml s3://tetetest/swagger.yaml
```

## パッケージ化
```
sam package --output-template-file packaged.yaml --s3-bucket tetetest
```

## デプロイ
```
sam deploy --template-file packaged.yaml  --stack-name sam-app  --capabilities CAPABILITY_IAM
```  

# テスト
## コンテナイメージ
```
git clone https://github.com/weseek/growi-docker-compose.git growi
```
## Growiへのアクセス
```
http://localhost:3000/
```
