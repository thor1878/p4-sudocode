lexer grammar SudoLexer;
tokens { NEWLINE, INDENT, DEDENT, ERR_DEDENT }

@header {
    package dist;
    import helpers.*;
}

@members {
    LexerHelper helper = new LexerHelper();
    
    @Override
    public Token nextToken() { return helper.nextToken(super::nextToken); }
}

L_PAREN: '(';
R_PAREN: ')';
L_CURLY: '{';
R_CURLY: '}';
L_BRACK: '[';
R_BRACK: ']';
DOLLAR: '$';
ADD: '+';
SUB: '-';
MULT: '*';
DIVD: '/';
MOD: '%';
COLON: ':';
SEMI: ';';
COMMA: ',';

COMPARISON: '==' | '!=' | '>=' | '>' | '<=' | '<';

RULE: 'rule';
APPLY: 'apply';
EXPECTS: 'expects';
FOR: 'for';
IF: 'if';
TO: 'to';
FROM: 'from';
OR: 'or';
AND: 'and';
GLOBAL: 'CELLS' | 'ROWS' | 'COLUMNS' | 'BOXES';

NUMBER: '-'? POS_NUMBER;
POS_NUMBER: '0' | [1-9][0-9]*;

ID: [A-Za-z]+;

WHITESPACE: ('\r'? '\n' ' '*)+;

IGNORE: ' ' -> skip;
COMMENT: '#' (~('\r'|'\n'))* -> skip;
