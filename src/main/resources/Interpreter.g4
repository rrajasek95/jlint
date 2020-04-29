grammar Interpreter;

start:
    operation EOF
    ;

operation
    : left=NUMBER operator=OPERATOR right=NUMBER
    ;

OPERATOR: '+' | '-' | '*' | '/' ;

NUMBER
    : ('-')? ('0' .. '9')+ ('.' ('0' .. '9') + )?
    ;

WS : (' ' | '\t')+ -> channel(HIDDEN);