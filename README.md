# java project

<p>
0.1초 주기로 데이터를 저장하고, 1초 주기로 데이터를 조회하여 마스터에서 슬레이브(백업)으로 전달하여 저장하는 프로그램입니다.
</p>

<h2>1. build and run</h2>
<h2>2. 동기화 테스트 예시</h2>
<h2>3. db(mysql, h2)<h2>

----------------------------------------------------------------------------------------------------------------

<h3> build and run</h3>
<p>프로젝트 루트(masterslave)로 이동 후 명령어 입력<br>
<code>./gradlew clean bootJar</code>
<p>

<p>./build/libs에 생성 된 master-slave-{version}-SNAPSHOT.jar 실행 *** 서로 다른 터미널에서 실행해주세요. ***<br>
<code>java -jar master-slave-{version}-SNAPSHOT.jar --spring.profiles.active=master -- 마스터</code><br>
<code>java -jar master-slave-{version}-SNAPSHOT.jar --spring.profiles.active=slave -- 슬레이브(백업)</code>
<p>

<h3> 동기화 테스트 예시</h3>
<p>
슬레이브(백업) 서버를 중지한 후 다시 실행했을 때 마스터와 동기화되는 테스트<br>
<ol>
    <li>마스터와 슬레이브 둘 모두 실행</li>
    <li>슬레이브만 중지한다</li>
    <li>마스터와 슬레이브 테이블 데이터가 동기화가 안되는걸 확인한다</li>
    <li>슬레이브를 실행한 후 동기화된 테이블 데이터를 확인한다</li>
</ol>
<code>SELECT COUNT(*) FROM TB_MASTER;
 SELECT COUNT(*) FROM TB_SLAVE;
</code>
*** 테이블을 초기화할 때는 offset.txt 파일을 삭제해야합니다.***
</p>

<h3> db(mysql, h2)</h3>
<p>
<code># db 환경에 맞게 username,password 설정 - mysql로 기본 설정
# h2 - 버전에 따라 데이터 베이스를 원격에서 만들어 주지않기 때문에 직접 데이터베이스를 만들어야하는 경우도 있습니다.
url: jdbc:log4jdbc:h2:tcp://localhost/~/test[데이터베이스명]
username: sa
password: 1234
# mysql
url: jdbc:log4jdbc:mysql://localhost/test[데이터베이스명]
username: username
password: password
</code>

</p>
