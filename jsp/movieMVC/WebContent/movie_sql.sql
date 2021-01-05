CREATE TABLE movie(
	movieNo number primary key, -- �쁺�솕 踰덊샇
	movieName VARCHAR2(20), -- �쁺�솕  �젣紐�
	category number, -- �옣瑜�
	runtime number, -- 120遺�
	img VARCHAR2(50), -- �씠誘몄� �뙆�씪 遺덈윭�삤湲� �쐞�븳 �젣紐�
	info VARCHAR2(200) -- �쁺�솕�젙蹂�
);

CREATE TABLE member(
	id		VARCHAR2(20) PRIMARY KEY, --�븘�씠�뵒
	pw		VARCHAR2(20), -- 鍮꾨�踰덊샇
	email	VARCHAR2(50), -- �씠硫붿씪
	tel		VARCHAR2(20), -- �쟾�솕踰덊샇
	birth	date
);

CREATE TABLE schedule(
	schNo	number	primary key, --�뒪耳�以� 踰덊샇
	movieNo	number, -- �쁺�솕愿� 踰덊샇
	runDay	date, -- �긽�쁺 �궇吏�
	runtime	number, -- �긽�쁺 �떆媛� : 紐뉖텇 吏쒕━ �쁺�솕
	roomNo	number -- �긽�쁺愿� 踰덊샇
);

CREATE TABLE Room( --�긽�쁺愿�
	roomNo	NUMBER, -- �긽�쁺愿� 踰덊샇
	schNo	NUMBER, -- �뒪耳�以� 踰덊샇
	seatCnt NUMBER -- �긽�쁺愿� �궡�뿉 �삁留ㅻ맂 醫뚯꽍 移댁슫�듃
);

CREATE TABLE ticket(
	ticketNo NUMBER PRIMARY KEY, -- �떚耳� 踰덊샇
	bookDate date, -- 寃곗젣�븳 �궇吏�
	schNo	NUMBER, -- �뒪耳�以� 踰덊샇
	seatNo	NUMBER, -- �궡媛� �꽑�깮�븳 醫뚯꽍 踰덊샇
	id		VARCHAR2(20) -- �쉶�썝 �븘�씠�뵒
);

insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

insert into member values('test','1234','test@test.com','010-1234-1234','2002-05-12');

                                           --sysdate varchar2 -> date 
insert into schedule values(1,10000,TO_DATE('2020/11/11 11:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(2,10000,TO_DATE('2020/11/11 1:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(3,10000,TO_DATE('2020/11/11 3:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(4,10000,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,1);

                          --좌석 번호 : 1 - 20 
insert into ticket values(1,SYSDATE,1,15,'test');
insert into ticket values(2,SYSDATE,1,14,'test');

                  --상영관 --스케줄번호 --예매좌석 카운트 
insert into room values(1, 1, 1);
insert into room values(1, 2, 0);
insert into room values(1, 3, 0);
insert into room values(1, 4, 0);
--티켓이 insert 될 때마다 seatCnt(예매한 좌석수 ) 갯수도 증가해야한다 
update room set seatCnt = seatCnt +1 where schNo = 1; 

SELECT movieName, category, img, mt.runtime, info, st.roomNo, st.schNo, runDay, seatCnt from movie mt, schedule st, Room rt where mt.movieNo = st.movieNo and rt.schNo = st.schNo and mt.movieNo = 10000;

SELECT * FROM schedule st, Room rt, movie mt WHERE st.movieNo = mt.movieNo and st.schNo = rt.schNo;

SELECT movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st WHERE tt.schno = st.schno and st.movieNo = 10000;

DROP TABLE ticket;

SELECT * FROM schedule, ticket, Room, movie WHERE ticket.schNo = schedule.schNo AND schedule.schNo = Room.schNo AND schedule.movieNo = movie.movieNo AND ticket.id = 'test';

SELECT schedule.schNo, schedule.movieNo, schedule.runtime, roomNo TO_CHAR(schedule.runDay, 'YYYY-MM-DD HH24:MI') as runDay,Room.seatCnt FROM schedule, Room WHERE movieNo = ? AND schedule.schNo = Room.schNo AND Room.seatCnt < 20;

-- schNo에 해당하는 좌석 번호 가져오기

SELECT seatNo FROM schedule, ticket WHERE ticket.schNo = schedule.schNo;

DROP TABLE movie;
DROP TABLE member;
DROP TABLE ticket;
DROP TABLE schedule;
DROP TABLE Room;

SELECT distinct roomNo FROM schedule;

SELECT * FROM schedule WHERE (runDay < TO_DATE('2020-12-06 17:40', 'YYYY-MM-DD HH24:MI') AND runDay + (runtime / 60) / 24 > TO_DATE('2020-12-06 17:40', 'YYYY-MM-DD HH24:MI')) OR (runDay < TO_DATE('2020-12-06 17:40', 'YYYY-MM-DD HH24:MI') + (runtime / 60) / 24 AND TO_DATE('2020-12-06 17:40', 'YYYY-MM-DD HH24:MI') + (runtime / 60) / 24 < runDay + (runtime / 60) / 24);

SELECT DISTINCT movie.movieName, movie.movieNo, movie.category, movie.runtime, movie.img, movie.info, NVL(room.seatCnt, 0) as seatCnt FROM movie LEFT OUTER JOIN schedule ON movie.movieNo = schedule.movieNo LEFT OUTER JOIN Room ON schedule.schNo = room.schNo ORDER BY seatCnt DESC;

DELETE FROM SCHEDULE WHERE schNo >= 5;

DELETE FROM ROOM WHERE schNo >= 5;

DELETE FROM Room WHERE schNo = 0;

DELETE FROM movie WHERE movieNo >= 10007;

SELECT distinct movie.*,sum(NVL(ticket.seatCnt, 0)) as seatCnt FROM movie LEFT OUTER JOIN schedule ON movie.movieNo = schedule.movieNo LEFT OUTER JOIN (SELECT schNo, COUNT(*) as seatCnt FROM ticket GROUP BY schNo) ticket ON schedule.schNo = ticket.schNo GROUP BY (movie.movieNo, movie.movieName, movie.runtime, movie.category, movie.runtime, movie.img, movie.info) ORDER BY seatCnt DESC;

SELECT distinct movie.*,sum(NVL(ticket.seatCnt, 0)) as seatCnt FROM movie LEFT OUTER JOIN schedule ON movie.movieNo = schedule.movieNo LEFT OUTER JOIN (SELECT schNo, COUNT(*) as seatCnt FROM ticket GROUP BY schNo) ticket ON schedule.schNo = ticket.schNo GROUP BY (movie.movieNo, movie.movieName, movie.runtime, movie.category, movie.runtime, movie.img, movie.info) ORDER BY seatCnt DESC;

SELECT movie.movieNo, movie.movieName, movie.category, movie.img, movie.info, movie.runtime, sum(NVL(schedule.seatCnt, 0)) as seatCnt FROM movie LEFT OUTER JOIN (SELECT schedule.schNo, schedule.movieNo, Room.seatCnt FROM Room LEFT OUTER JOIN schedule ON Room.schNo = schedule.schNo) schedule ON movie.movieNo = schedule.movieNo GROUP BY (movie.movieNo, movie.movieName, movie.category, movie.img, movie.info, movie.runtime) ORDER BY seatCnt DESC;

DELETE FROM ticket WHERE schNo = 7;