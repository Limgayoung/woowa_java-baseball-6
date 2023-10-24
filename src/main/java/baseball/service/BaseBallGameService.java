package baseball.service;

import baseball.model.BaseBallNumber;
import baseball.model.BaseBallResult;
import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

public class BaseBallGameService {
    //game start
    public void gameStart() {
        printStartNotice();
        while (true) {
            playGame();
            //재시작 여부 확인
            printRestartNotice();
            if (!isRestartGame()) {
                break;
            }
        }
        //완전 종료
    }

    void playGame() {
        //1. 숫자 생성
        BaseBallNumber computerNum = initComputerNum();

        //2. 게임 진행
        while (true) {
            BaseBallNumber userNum = initUserNum();
            BaseBallResult gameResult = userNum.compareNumber(computerNum);
            printGameResult(gameResult);
            if (isGameFinish(gameResult)) {
                printGameResult();
                break;
            }
        }
    }

    private BaseBallNumber initComputerNum() {
        int first = Randoms.pickNumberInRange(1, 9);
        int second = Randoms.pickNumberInRange(1, 9);
        int third = Randoms.pickNumberInRange(1, 9);

        return new BaseBallNumber(first, second, third);
    }

    private BaseBallNumber initUserNum() {
        String userInput = Console.readLine();
        try {
            int userInputNum = Integer.parseInt(userInput);

            if (userInputNum > 999 || userInputNum < 100) {
                throw new IllegalArgumentException();
            }

            int first = userInputNum / 100;
            int second = userInputNum / 10 % 10;
            int third = userInputNum % 10;
            return new BaseBallNumber(userInputNum / 100, userInputNum / 10 % 10, userInputNum % 10);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    boolean isGameFinish(BaseBallResult result) {
        if (result.strike().equals(3)) {
            return true;
        } else {
            return false;
        }
    }

    void printUserInputNotice() {
        System.out.print("숫자를 입력해주세요 : ");
    }

    void printGameResult(BaseBallResult result) {
        if (result.ball().equals(0) && result.strike().equals(0)) {
            System.out.println("낫싱");
            return;
        }
        if (result.ball() > 0) {
            System.out.print(result.ball().intValue() + "볼 ");
        }
        if (result.strike() > 0) {
            System.out.println(result.strike().intValue() + "스트라이크");
        }
    }

    void printGameResult() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
    }

    void printRestartNotice() {
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
    }

    boolean isRestartGame() {
        String userInput = Console.readLine();
        try {
            int userInputNum = Integer.parseInt(userInput);

            if (userInputNum == 1) {
                return true;
            } else if (userInputNum == 2) {
                return false;
            } else {
                throw new IllegalArgumentException();
            }

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    private void printStartNotice() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }
}
