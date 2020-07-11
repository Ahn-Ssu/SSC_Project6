# SCC_Project 6.
##### 2020 Summer Coding Camp - Project #6 (Prof. KIM HJ)
###### 21600402 Ahn SuHyun


## Task : Connect6 (육목) + AI
### Time Period : 3day






#### 아이디어 흐름
1. 컴퓨터에게 돌의 정보를 알려주는 것에 대해서 #지뢰찾기#를 모티브로 코드 방향을 잡게 됨 
2. 지뢰찾기처럼 모드 값을 한번에 합쳐버리게 되면 더미벨류(쓰레기값)이 필터링이 안되고 공격도 방어도 효율적이지 못함
3. 필터를 4개를 끼워서  { X축, Y축, RS축 : 우상대각, LS축 : 좌상대각 } 4개의 각각의 방향에 유효값을 따로따로 저장하 
4. 4방 필터를 사용하여 공격을 해야할땐 공격할 수 있고, 방어를 해야할땐 방어를 하는 1세대 (Lv... 1?) 정도 완성
5. 1세대의 문제점은 6개를 못만드는 상황임에도 불구하고, 일단 가장 높은 값을 찾아가 공격 및 방어를 하여 본인에게 공격의 흐름이 있어도 금방 반납하고 의미없는 착수를 많이 함. 그러다보니 공격이 매우 단순하며 게임이 진행 될 수록 그 공격권의 흐름을 유지하지 못함. 
6. 의미없는 착수를 줄이기 위해 4연속으로 돌이 있을때, 앞으로 둘 수가 유효한지 검색하는 필터를 만들어줌 (1.5세대)
7. 1.5세대라고 표기한 이유는 5연속돌에게도 4연속 필터의 조건을 만족해서 5연속 돌 앞에 한칸을 비우고 상대돌을 위치시키면 유효하지 않은 '4연석'이라고 체크하여 착수를 안하는 문제가 있었음 
8. 그래서 5연속 필터도 만들어서 추가해줌 (2세대)
9. 왠만한 단순착수는 전부 잡아내지만, 4방필터의 탐지거리가 1칸밖에 유효하지 않기 때문에 돌의 갯수가 4개이지만, 2칸의 공백이 그 사이에 들어가 있으면 방어 or 공격을 하지 못함
10. 그런 모든 경우를 컴퓨터에게 미리 알려주어 패턴이 검출되면 가중치를 최상위로 올려주어 무조건 그 위치에 착수하게 설계함 (3세대) 


##### 버그 및 구현 못한 거 
1. 필터에서 탐지영역 설정을 잘못해서 구석으로 가서 특정조건을 만족하면 에러뜸.
2. 4필터 5필터의 연결되는 오류인지 잘 막다가 수가 엄청나게 늘어나면 고장남 
3. 4X4의 수를 유도도 못하며, 방어도 사전에 하지 못함 
4. 공격이랑 방어가 상당히 단순하다. 붙여서 착수하는 것밖에 만들어지지 않음 
5. 필터의 로직이 방어나 공격이 동일하다고 생각해서 메소드로 떼서 똑같이 행동하게 해놨는데 분리해서 만들었어도 괜찮지 않았을까 싶음 


## 배운점 
1. AI를 좀 무식하게 0부터 1까지 다 짜고, 알려주면서 했는데 생각보다 재미있었다. 다른사람 소스코드 보면서 참고해서 살을 붙여도 재밌을듯 
