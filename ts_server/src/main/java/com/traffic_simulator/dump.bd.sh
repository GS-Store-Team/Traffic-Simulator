rm dump.sql
pg_dump -h 127.0.0.1 -U admin traffic -f dump.sql