class Main {
    public static void main(String[] args) {
        for (int i=0; i<100; i++) {
            System.out.println("Dette er linje nummer" + i);
            try {
            Thread.sleep(10);
            }
            catch (Exception e) {
                System.err.println("En error");
            }
        }
    }
}