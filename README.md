# 자동차 경주 게임
## 진행 방법
* 자동차 경주 게임 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1. 학습 테스트 실습 Todo-list
- 모든 테스트는 DisplayName 어노테이션을 통해 테스트 의도를 명확히 드러낸다.

### String 클래스 학습 테스트
- [X] "1,2"을 ,로 split 했을 때 1과 2로 잘 분리되는지 확인하는 학습 테스트를 구현한다.
- [X] "1"을 ,로 split 했을 때 1만을 포함하는 배열이 반환되는지에 대한 학습 테스트를 구현한다.
- [X] "(1,2)" 값이 주어졌을 때 String의 substring() 메소드를 활용해 ()을 제거하고 "1,2"를 반환하도록 구현한다.
- [X] "abc" 값이 주어졌을 때 String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져오는 학습 테스트를 구현한다.
    - [X] String의 charAt() 메소드를 활용해 특정 위치의 문자를 가져올 때 위치 값을 벗어나면 StringIndexOutOfBoundsException이 발생하는 부분에 대한 학습 테스트를 구현한다.

### Set Collection 학습 테스트
- 제시된 테스트 데이터는 다음과 같다.
```java
public class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }
}
```
- [X] Set의 size() 메소드를 활용해 Set의 크기를 확인하는 학습테스트를 구현한다.
- [X] Set의 contains() 메소드를 활용해 1, 2, 3의 값이 존재하는지를 확인하는 학습테스트를 구현한다.
    - [X] JUnit의 ParameterizedTest를 활용해 중복 코드를 제거해 본다.
- [X] 위 요구사항은 contains 메소드 결과 값이 true인 경우만 테스트 가능하다. 입력 값에 따라 결과 값이 다른 경우에 대한 테스트도 가능하도록 구현한다.
    - 예를 들어 1, 2, 3 값은 contains 메소드 실행결과 true, 4, 5 값을 넣으면 false 가 반환되는 테스트를 하나의 Test Case로 구현한다.

## Step2. 문자열 계산기
### 제약조건
- 사용자는 문자열을 입력한다.
- 숫자와 사칙 연산 사이에는 반드시 공백이 존재한다.(=구분자는 공백이다)
- 나눗셈은 몫만 반환한다.
- 사칙연산의 수학적 우선순위는 무시하고 입력된 순서로 사칙연산을 진행한다.

### Todo-list
- Number(Class)
    - 계산기에서 사용될 숫자를 래핑하는 값 객체
    - [X] 문자열을 인자로 받아 객체를 생성할 수 있다.
        - 단, 전달된 문자열을 숫자로 변경할 수 없는 경우 예외를 발생시킨다.
        - 숫자 범위는 정수로 한정한다.(양의 정수, 음의 정수 모두 존재한다.)
- Operator(Enum)
    - 계산기에서 사용되는 사칙 연산 객체
    - [X] 문자열로 된 사칙 연산을 전달 받아서 알맞는 사칙연산 객체를 반환할 수 있다.
    - [X] Number 객체 두개를 전달 받아서 사칙 연산을 수행할 수 있다.
        - 나눗셈의 경우 0으로 나누기를 시도하는 경우 예외를 발생시킨다.
- Calculator(Class)
    - 사용자가 입력한 문자열 수식을 계산하는 객체
    - [X] Number 컬렉션, Operator 컬렉션을 인스턴스 변수로 갖는다.
    - [X] Operator 컬렉션과 Number 컬렉션의 크기를 통해 정합성을 확인한다.
        - [X] `Number 컬렉션의 수 != Operator 컬렉션의 수` 경우 예외
        - [X] 사칙연산자만 있거나 숫자만 있는 경우 예외
    - [X] 객체의 상태를 기반으로 계산을 수행할 수 있다.
        - [X] 연산자가 1개일 때 계산을 수행할 수 있다.
        - [X] 연산자가 2개 이상일 때 계산을 수행할 수 있다.
- CalculatorFactory(Class)
    - 문자열 수식을 인자로 받아서 계산기 객체를 생성하는 객체
    - [X] 인자로 전달된 정합성을 확인한다.
        - [X] 빈 문자열인 경우 예외
        - [X] 사칙 연산자로 시작하는 문자열의 경우 예외 (ex. + 3 - 2)
        - [X] 숫자로 끝나지 않는 문자열의 경우 예외 (ex. 3 + 2 -)
        - [X] 사칙연산자가 없는 문자열의 경우 예외 (ex. 3 2, 2)
        - [X] 숫자가 없는 문자열의 경우 예외 (ex. + -)
    - [X] 정합성이 확인된 문자열로 Calculator 객체를 생성할 수 있다.

## Step2. 피드백 반영
- [X] CalculatorFactory 클래스의 객체 생성 메서드명 변경
    - 일반적으로 of는 사용한 해당 클래스 타입을 반환할 것이라 기대되기 때문에 현재 형태와는 어울리지 않음
- [X] 빈 String 비교할 때 NPE가 발생하지 않도록 안전한 코드로 수정

## Step3. 자동차 경주
### 제약조건
- 자동차의 연료 값의 범위는 0 ~ 9이다.
- 자동차의 연료가 4이상일 때만 전진할 수 있다.
- 각 회차 별로 결과가 모여서 보여야 한다.
- 각 회차 진행 시 모든 자동차가 동시에 움직인다.

### Todo-list
- Fuel(class)
    - 자동차가 움직이기 위해 필요한 연료를 표현하는 래퍼 객체
    - [X] 0 ~ 9 사이의 값을 갖는다.
- GasStation(interface)
    - 연료 생산 방식을 정의하는 인터페이스
    - [X] Fuel 객체를 생산할 수 있다.
- RandomGasStation(class)
    - 랜덤으로 연료를 생산하는 객체
    - [X] 랜덤으로 유효한 범위의 Fuel 객체를 생산할 수 있다.
- Car(class)
    - 연료를 주입받아 움직일 수 있는 객체
    - [X] 연료의 값에 따라 이동할 수 있다.
        - 연료값 4이상: 이동 가능 / 연료값 4미만: 이동 불가능
    - [X] GasStation 객체를 인자로 받아서 객체를 생산할 수 있다.
- Cars(class)
    - Car 일급 컬렉션
    - [X] 만들 Car의 수를 인자로 받아서 객체를 생성할 수 있다.
    - [X] 속한 Car 객체를 모두 동시에 이동시킬 수 있다.
    - [X] 현재 속한 차량들의 이동 거리를 확인할 수 있다.

## Step3. 피드백 반영
- [X] CalculatorFactory 클래스의 메서드명을 역할에 어울리도록 수정
    - 다음부턴 똑바로 정신 차리고 이름 잘 확인할 것
- [X] 전략객체로 사용되고 있는 GasStation 이름 어떻게 가져갈지 고민할 것
    - [X] 움직일 수 있는지 판단하는 기능을 GasStation 구현체에서 할지 Fuel에서 할지 고민해볼 것
    - Fuel 객체가 반드시 필요한지에 대해 고민 필요
- [ ] RacingGame이 진짜 필요한 객체인지 고민할 것
    - Step4 구현하면서 고려할 것
    
## Step4. 자동가 경주(우승자)
### 제약조건
- 각 자동차에 이름이 있다.
    - 자동차의 이름은 5글자를 넘길 수 없다.
- 플레이어에게 결과를 보여줄 때 어떤 자동차가 얼마나 움직였는지 보여줘야 한다.
- 모든 경기가 끝나면 플레이어에게 우승자를 알려줘야 한다.

### Todo-list
- Car(class)
    - [X] 기존 구현체에 이름 속성을 추가한다.
    - [X] 이름을 인자로 전달해서 오브젝트를 생성할 수 있도록 개선한다.
- Name(class)
    - 자동차 이름을 표현하는 오브젝트
    - [X] 문자열을 인자로 받아 객체를 생성할 수 있다.
        - [X] 정합성을 확인한다.
            - 빈 문자열여부, 이름 최대 길이 5자를 넘지 않는지 확인
            - 공백, 특수문자, 숫자 등도 일반 문자열로 취급한다.
- CarsFactory(class)
    - 복잡한 Cars 일급 컬렉션 생성방식을 책임지는 객체
    - [X] Name 컬렉션을 인자로 받아서 Cars 일급 컬렉션 생성 가능
    - [X] Name 컬렉션 유효성 검증
        - null이거나 빈 컬렉션일 경우 예외 발생
- InputView(class)
    - 사용자가 입력한 차량 목록을 담는 일종의 DTO 오브젝트
    - [X] 정합성 검증을 진행한다.
        - 빈 문자열 여부
    - [X] Name 컬렉션으로 파싱할 수 있다.
        - 정합성 조건: 이름 길이
- Cars(class)
    - [X] 복잡해진 객체 생성 책임을 팩토리 메서드로 이관한다.
    - [ ] 우승 차량들을 계산할 수 있다.
- RacingGamRunner
    - [X] 게임 시작 방식을 차량 대수 입력에서 참여할 차량 이름으로 변경한 뒤 기존 기능 그대로 동작하는지 확인
    - [X] 레이싱 게임 결과 출력 시 차량 이름도 출력되도록 기능 변경
    - [ ] 우승 차량 결과를 사용자가 볼 수 있도록 기능 추가
