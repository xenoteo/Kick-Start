package xenoteo.com.github.year2020.B.robotPathDecoding;

import java.util.Scanner;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int testCasesNumber = in.nextInt();
        for (int t = 0; t < testCasesNumber; t++){
            String program = in.next();
            System.out.printf("Case #%d: %s\n", t + 1, countRoverFinishPosition(program));
        }
    }

    public static String countRoverFinishPosition(String program){
        Position position = new Position(1, 1);
        char[] moves = program.toCharArray();
        Stack<Integer> timesStack = new Stack<>();  // stack keeping numbers of times to repeat certain subprograms
        Stack<Delta> subprogramsStack = new Stack<>();  // stack keeping deltas of subprograms
        int i = 0;
        while (i < moves.length){
            // if a character is a number, then pushing the new number of times to repeat a new subprogram,
            // as well as pushing and creating new delta object for the new subprogram
            if (moves[i] >= '2' && moves[i] <= '9'){
                timesStack.push(moves[i] - '0');
                subprogramsStack.push(new Delta());
                i += 2;
            }
            // if a character is a closing bracket then the last subprogram is finished
            else if (moves[i] == ')'){
                int times = timesStack.pop();
                Delta subprogram = subprogramsStack.pop();
                // if there are no other subprograms in the stack then we can apply the delta directly to the position
                if (timesStack.empty()){
                    position.update(subprogram.repeat(times));
                }
                // otherwise we need to update the subprogram at the top of the stack
                // applying the current delta to it
                else {
                    subprogramsStack.peek().update(subprogram.repeat(times));
                }
                i++;
            }
            // if a character is just a simple move
            else {
                // if there are no subprograms in the stack then we can directly update the position
                if (subprogramsStack.empty()){
                    position.update(new Delta().update(moves[i]));
                }
                // otherwise we need to update the subprogram at the top
                else {
                    subprogramsStack.peek().update(moves[i]);
                }
                i++;
            }
        }
        return position.w + " " + position.h;
    }

    /**
     * Class keeping the delta of width and height of a position.
     */
    private static class Delta{
        long w = 0;
        long h = 0;
        static final long MAX_SIZE = 1_000_000_000;

        /**
         * Updates the delta according to a move provided.
         *
         * @param direction  the direction to make move to
         * @return  this updated object
         */
        public Delta update(char direction){
            switch (direction){
                case 'N':
                    h--;
                    break;
                case 'S':
                    h++;
                    break;
                case 'W':
                    w--;
                    break;
                case 'E':
                    w++;
                    break;
            }
            minimize();
            return this;
        }

        /**
         * Updates the delta according to a delta provided.
         *
         * @param delta  the delta to apply to the current delta object
         * @return  this updated object
         */
        public Delta update(Delta delta){
            this.w += delta.w;
            this.h += delta.h;
            minimize();
            return this;
        }

        /**
         * Repeats the delta provided number of times.
         *
         * @param times  the number of times to repeat the delta
         * @return  this updated object
         */
        public Delta repeat(int times){
            w *= times;
            h *= times;
            minimize();
            return this;
        }

        /**
         * Minimizes the delta, since the planet is a torus and the first and last columns are adjacent,
         * and delta representing MAX_SIZE multiplied by some number will lead to the same answer
         * as delta representing just MAX_SIZE.
         */
        private void minimize(){
            w %= MAX_SIZE;
            h %= MAX_SIZE;
        }
    }

    /**
     * Class representing the Rover's position.
     */
    private static class Position{
        long w;
        long h;
        static final long MAX_SIZE = 1_000_000_000;

        public Position(long w, long h) {
            this.w = w;
            this.h = h;
        }

        /**
         * Updates the position according to the provided delta.
         *
         * @param delta  the delta to apply to the current positon
         */
        public void update(Delta delta){
            w += delta.w;
            if (w < 1) w += MAX_SIZE;
            if (w > MAX_SIZE) w -= MAX_SIZE;
            h += delta.h;
            if (h < 1) h += MAX_SIZE;
            if (h > MAX_SIZE) h -= MAX_SIZE;
        }
    }
}