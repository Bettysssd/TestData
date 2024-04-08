package queue;

public class TestBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue1<String> queue = new BlockingQueue1<>(3);

        Thread t1 = new Thread(()-> {
            try {
                System.out.println(System.currentTimeMillis() + " begin");
                queue.offer("mission1");
                System.out.println(queue);
                queue.offer("mission2");
                System.out.println(queue);
                queue.offer("mission3");
                System.out.println(queue);
                queue.offer("mission4",5000);
                System.out.println(queue);
                System.out.println(System.currentTimeMillis() + " end");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "生产者");
        t1.start();

        Thread.sleep(2000);
        queue.poll();
    }
}
