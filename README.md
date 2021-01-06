# projectPortfolio

여기 사이트에 있는 프로젝트 확인하기 전에 주의사항

혹시나 프로젝트를 직접 다운 받아 실행하실 거라면 몇몇 프로젝트는 db 문제로 실행이 안 될 경우가 있습니다.
여기에 db 때문에 실행인 안 되는 프로젝트 적어놓을테니 참고 하면 좋을 것 같습니다.

java 프로젝트에서 실행 안 되는 것
picross

web 프로젝트에서 실행 안 되는 것
없음.

jsp 프로젝트에서 실행 안 되는 것 
없음

jsp는 무조건 webcontent 안에 있는 sql 문을 실행시키고 나서 프로젝트를 실행해야 합니다.
oracle을 사용하기 때문에 oracleDB가 없다면 oracle-xe를 다운받아 설치한 뒤 sql을 실행하세요
db userId와 userPw는 각 DAO 안에 getConnection 매서드 안에 있습니다
