package ladder.domain;

class Row {
    private enum Direction {
        LEFT(-1),
        CENTER(0),
        RIGHT(1);

        private int number;

        Direction(int number) {
            this.number = number;
        }

        int getNumber() {
            return number;
        }
    }

    private int[] persons;

    Row(NaturalNumber noOfPersons) {
        this.persons = new int[noOfPersons.getNumber()];
    }

    void drawLine(NaturalNumber startPosition) {
        int startIndex = startPosition.toArrayIndex();
        if (startIndex >= persons.length - 1) {
            throw new IllegalArgumentException(String.format("시작점은 &d 미만이어야 합니다. 현재 값 : %d", persons.length - 1, startPosition));
        }

        int currentValue = persons[startIndex];
        if(currentValue == -1) {
            throw new IllegalArgumentException("선을 그을 수 없는 위치입니다.");
        }
        persons[startIndex] = Direction.RIGHT.getNumber();
        persons[startIndex + 1] = Direction.LEFT.getNumber();
    }

    int move(int startMarker) {
        if (startMarker < 0) {
            throw new IllegalArgumentException(String.format("사다리 시작 위치는 0이상이어야 합니다. 현재 값: %d", startMarker));
        }

        if (isNoLine(startMarker)) {
            return startMarker;
        }
        if (isRightDirection(startMarker)) {
            return startMarker + 1;
        }
        return startMarker - 1;
    }

    private boolean isRightDirection(int nthOfPerson) {
        return persons[nthOfPerson] == Direction.RIGHT.getNumber();
    }

    private boolean isNoLine(int nthOfPerson) {
        return persons[nthOfPerson] == Direction.CENTER.getNumber();
    }
}
