package interpreter;
public enum Simboli {
    CHAR_INVALIDO,
    nessun_simbolo, 
    NEW,//nota che sarebbe "new" ma questa parola è protetta
    del, 
    mv, 
    mvoff,
    scale,
    ls,
    ls_all,//nota che sarebbe ls all
    ls_groups,//ls groups
    grp,
    ungrp,
    area,
    area_all,//area all
    perimeter,
    perimeter_all,//perimeter all
    circle,
    rectangle,
    img,
    EOF,
    all, 
    groups, 
    PAROLA,
    TONDA_APERTA,
    TONDA_CHIUSA, 
    posfloat,
    PATH,
    VIRGOLA,
    ObjID
}
