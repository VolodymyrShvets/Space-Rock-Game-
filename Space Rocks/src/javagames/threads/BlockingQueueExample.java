package javagames.threads;

import java.util.Random;
import java.util.concurrent.*;

public class BlockingQueueExample {
    class Producer implements Callable<Void> {
        private Random rand = new Random();
        private int numberOfMessages;
        private int sleep;

        private Producer(int numberOfMessages, int sleep) {
            this.numberOfMessages = numberOfMessages;
            this.sleep = sleep;
        }

        @Override
        public Void call() throws Exception {
            Message[] messages = Message.values();
            for (int i = 0; i < numberOfMessages; i++) {
                try {
                    // don't include last message (POISON)
                    int index = rand.nextInt(messages.length - 2); // 0-2

                    queue.put(messages[index]);
                    System.out.println("PUT(" + (i + 1) + ')' + messages[index]);
                    sleep(sleep);
                } catch (InterruptedException ex) {
                }
            }
            // All done. Shut her down...
            queue.put(messages[messages.length - 1]);
            return null;
        }
    }

    class Consumer implements Callable<Integer> {
        private int messageCount = 0;

        @Override
        public Integer call() throws Exception {
            while (true) {
                // take will block forever unless we do something
                Message msg = queue.take();
                messageCount++;
                System.out.println("Received: " + msg);
                if (msg == Message.POISON_PILL)
                    break;
            }
            return new Integer(messageCount);
        }
    }

    enum Message {
        MESSAGE_ONE,
        MESSAGE_TWO,
        MESSAGE_THREE,
        POISON_PILL // Quit
    }

    private ExecutorService exec;
    private BlockingQueue<Message> queue;

    public BlockingQueueExample() {
        exec = Executors.newCachedThreadPool();
        queue = new LinkedBlockingQueue<>();
    }

    public void runTest() {
        int numberOfMessages = 100;
        int sleep = 100;

        System.out.println("Message Sent: " + numberOfMessages);

        exec.submit(new Producer(numberOfMessages, sleep));
        // sleep a little
        sleep(2000);

        try {
            // Start the consumer much later, but that's ok
            Future<Integer> consumer = exec.submit(new Consumer());
            try {
                System.out.println("Messages Processed: " + consumer.get());
            } catch (ExecutionException | InterruptedException ex) {
            }
        } finally {
            try {
                exec.shutdown();
                exec.awaitTermination(10, TimeUnit.SECONDS);
                System.out.println("Threadpool Shutdown");
            } catch (InterruptedException ex) {
                // at this point, just give up...
                ex.printStackTrace();
                System.exit(-1);
            }
        }
    }

    protected void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
        }
    }

    public static void main(String[] args) {
        new BlockingQueueExample().runTest();
    }
}
