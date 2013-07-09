import org.joda.time.DateTime;

import java.util.Iterator;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class DateIterable implements Iterable<DateTime>, Iterator<DateTime> {
    private DateTime current;

    public DateIterable(DateTime current) {
        this.current = current;
    }

    @Override
    public Iterator<DateTime> iterator() {
        return this;

    }
        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public DateTime next() {
            DateTime result=current;
            current=current.plusDays(1);
            return result;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
}
