package patterns.iterator;

/**
 * @Author lishaohui
 * @Date 2023/4/12 22:32
 */
public class IteratorPattern {

    // 迭代器
    private interface Iterator {

        Object next();

        boolean hasNext();

    }

    private interface Container {
        Iterator getIterator();
    }

    private static class NameRepository implements Container {

        private final String[] names = {"Robert", "John", "Julie", "Lora"};

        @Override
        public Iterator getIterator() {
            return new NameIterator();
        }

        private class NameIterator implements Iterator {
            int index;

            @Override
            public Object next() {
                if (this.hasNext()) {
                    return names[index++];
                }
                return null;
            }

            @Override
            public boolean hasNext() {
                return index < names.length;
            }
        }
    }

    public static void main(String[] args) {
        NameRepository repository = new NameRepository();
        for (Iterator iterator = repository.getIterator();iterator.hasNext();){
            String name  = (String) iterator.next();
            System.out.println("Name : " + name);
        }
    }

}
