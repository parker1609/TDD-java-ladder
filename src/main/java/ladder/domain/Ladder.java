package ladder.domain;

import ladder.core.NaturalNumber;

class Ladder {
    private Row[] rows;

    Ladder(NaturalNumber height, NaturalNumber noOfPerson) {
        rows = new Row[height.getNumber()];
        for (int i = 0; i < height.getNumber(); i++) {
            rows[i] = new Row(noOfPerson);
        }
    }

    void drawLine(NaturalNumber height, NaturalNumber startPosition) {
        if (height.toArrayIndex() > rows.length - 1) {
            throw new IllegalArgumentException(String.format("사다리 최대 높이를 넘어섰습니다. 현재 값 : %d", height));
        }

        rows[height.toArrayIndex()].drawLine(startPosition);
    }

    Marker run(Marker nthOfPerson) {
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            nthOfPerson = row.move(nthOfPerson);
            String result = generate(rows, new NaturalNumber(i + 1), nthOfPerson);
            System.out.println(result);
        }
        return nthOfPerson;
    }

    public static String generate(Row[] rows, NaturalNumber height, NaturalNumber nthOfPerson) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows.length; i++) {
            Row row = rows[i];
            row.generateRow(sb, i, height, nthOfPerson);
        }
        return sb.toString();
    }
}
