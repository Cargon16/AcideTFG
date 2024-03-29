%
% Flights
%
% SQL formulation

/abolish
/show_compilations on
/multiline on

CREATE OR REPLACE TABLE flight(origin string, destination string, time real);

INSERT INTO flight VALUES('london','ny',9.0);
INSERT INTO flight VALUES('mad','par',1.5);
INSERT INTO flight VALUES('par','ny',10.0);

CREATE OR REPLACE VIEW travel(origin,destination,time) AS (
       SELECT * FROM flight 
       UNION 
       SELECT F.origin,C.destination,F.time+C.time 
       FROM flight F,flight C
       WHERE F.destination = C.origin);

/multiline off
