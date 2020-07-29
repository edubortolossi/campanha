package br.com.campanha.enums;

import lombok.Getter;

@Getter
public enum Time {
    ATHLETICO_PARANAENSE( 1, "Athletico Paranaense" ),
    ATLETICO_GOIANIENSE( 2, "Atlético Goianiense" ),
    ATLETICO_MINEIRO( 3, "Atlético Mineiro" ),
    BAHIA( 4, "Bahia" ),
    BOTAFOGO( 5, "Botafogo" ),
    CEARA( 6, "Ceara" ),
    CORINTHIANS( 7, "Corinthians" ),
    CORITIBA( 8, "Coritiba" ),
    FLAMENGO( 9, "Flamengo" ),
    FLUMINENSE( 10, "Fluminense" ),
    FORTALEZA( 11, "Fortaleza" ),
    GOIAS( 12, "Goias" ),
    GREMIO( 13, "Gremio" ),
    INTERNACIONAL( 14, "Internacional" ),
    PALMEIRAS( 15, "Palmeiras" ),
    RED_BULL( 16, "Red Bull" ),
    SANTOS( 17, "Santos" ),
    SAO_PAULO( 18, "São Paulo" ),
    SPORT( 19, "Sport Recife" ),
    VASCO( 20, "Vasco da Gama" );

    int id;
    String nome;

    Time( int id, String nome ) {
        this.id = id;
        this.nome = nome;
    }
}
