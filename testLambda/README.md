## jarの置き場作成
```
aws s3 mb s3://tetetest
```

## パッケージ化
```
sam package --output-template-file packaged.yaml --s3-bucket tetetest
```

## デプロイ
```
sam deploy --template-file packaged.yaml  --stack-name sam-app  --capabilities CAPABILITY_IAM
```  


