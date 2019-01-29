CREATE TABLE error_message (
  id               BIGSERIAL PRIMARY KEY,
  message_type     TEXT,
  message          TEXT,
  throwing_time    TEXT,
  file_Name        TEXT,
  type_Of_error    TEXT
);

CREATE TABLE exception_message (
  id                   BIGSERIAL PRIMARY KEY,
  message_type         TEXT,
  message              TEXT,
  throwing_time        TEXT,
  file_name            TEXT,
  type_Of_exception    TEXT
)


