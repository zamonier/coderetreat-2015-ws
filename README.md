# coderetreat-2015-ws
REST service for game of life

## Hi there!

This is man page about Game of Live web service

- You should use `/next` URL to get next generation of **Game Of Life**. 
- For this you should use `POST` method in request. 
- Just put a **JSON** array into request body. 
- Use only **1** and **0** numbers for marking alive or dead cells. 
- Also set contentType to `application/json`.

For example at http://game-of-life-ws.herokuapp.com/
```javascript
Request: [[1,0],[1,1]]
```

```javascript
Response: [[1,1],[1,1]]
```

