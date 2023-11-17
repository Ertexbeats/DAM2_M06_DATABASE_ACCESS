package cat.proven.mvc.views;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class Menu {
    protected String title;
    protected List< Option > options;

    public Menu( String title ) {
        this.title = title;
        this.options = new ArrayList< Option >();
    }

    public Menu() {
        this.title = null;
        this.options = new ArrayList< Option >();
    }


    /*======Methods=======*/
    public boolean addOption( Option option ) {
        return options.add( option );
    }

    public boolean removeOption( Option option ) {
        return options.remove( option );
    }

    public Option removeOption( int index ) {
        return options.remove( index );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Menu [title=" );
        builder.append( title );
        builder.append( ", options=" );
        builder.append( options );
        builder.append( "]" );
        return builder.toString();
    }

    public void show() {
        System.out.format( "============%s============\n", title );
        ListIterator< Option > it = options.listIterator();
        int idOption = 0;
        while ( it.hasNext() ) {
            System.out.format( "[%d] %s\n", idOption, it.next().getText() );
            idOption++;
        }
    }

    public int getSelectedOption() {
        Scanner sc = new Scanner( System.in );
        int opt = -1;
        try {
            System.out.print( "Select an option: " );
            opt = sc.nextInt();
            if ( ( opt < 0 ) || ( opt >= options.size() ) ) {
                opt = -1;
            }
        } catch ( InputMismatchException ime ) {
            opt = -1;
        }
        return opt;
    }

    public String getSelectedOptionActionCommand() {
        int optionNumber = getSelectedOption();
        String actionCommand = null;
        if ( ( optionNumber >= 0 ) && ( optionNumber < options.size() ) ) {
            actionCommand = options.get( optionNumber ).getActionCommand();
        }
        return actionCommand;
    }
}
