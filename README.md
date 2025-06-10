# Player Time Tracker

Пока поддерживается только postgres

Стандартный конфиг создаётся при первом запуске в config/pttracker/db.json:
```json
{
  "user": "test",
  "password": "",
  "dbHost": "localhost",
  "dbName": "testdb",
  "dbType": "POSTGRESQL"
}
```

Таблица создаётся при первом запуске:
```sql
CREATE TABLE playtime (
    uuid UUID PRIMARY KEY,
    play_time BIGINT
);
```