package cat.proven.mvc.views;

public class Option {

    /*====== ATTRIBUTES =======*/
    private String text;
    private String actionCommand;

    public Option( String text, String actionCommand ) {
        this.text = text;
        this.actionCommand = actionCommand;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public String getActionCommand() {
        return actionCommand;
    }

    public void setActionCommand( String actionCommand ) {
        this.actionCommand = actionCommand;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( actionCommand == null ) ? 0 : actionCommand.hashCode() );
        result = prime * result + ( ( text == null ) ? 0 : text.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj ) {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( !( obj instanceof Option ) )
            return false;
        Option other = ( Option ) obj;
        if ( actionCommand == null ) {
            if ( other.actionCommand != null )
                return false;
        } else if ( !actionCommand.equals( other.actionCommand ) )
            return false;
        if ( text == null ) {
            if ( other.text != null )
                return false;
        } else if ( !text.equals( other.text ) )
            return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append( "Option [text=" );
        builder.append( text );
        builder.append( ", actionCommand=" );
        builder.append( actionCommand );
        builder.append( "]" );
        return builder.toString();
    }
}
