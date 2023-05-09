package com.traffic_simulator.models.areasConfig;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
public class AreasPlacement {
    private List<Elem> elems;
    {
        elems = new ArrayList<>();
        elems.add(new Elem(0L, Arrays.asList(
                0L,     1L,     2L,     3L,     4L,
                20L,    21L,    22L,    23L,    24L,
                40L,    41L,    42L,    43L,    44L,    45L,
                60L,    61L,    62L,    63L,    64L,    65L,
                80L,    81L,    82L,    83L,    84L,    85L,
                100L,   101L,   102L,   103L,   104L,
                120L,   121L,   122L,   123L,   124L,
                140L,   141L,   142L,   143L,   144L,
                160L,   161L,   162L,   163L,   164L,
                180L,   181L,   182L,   183L,
                200L,   201L,   202L,
                220L,   221L,   222L,
                240L
                )));
        elems.add(new Elem(1L, Arrays.asList(
                5L,     6L,     7L,     8L,     9L,
                25L,    26L,    27L,    28L,    29L,
                46L,    47L,    48L,    49L,    50L,
                66L,    67L,    68L,    69L,    70L,
                86L,    87L,    88L,    89L,    90L,
                105L,   106L,   107L,   108L,   109L,   110L,
                125L,   126L,   127L,   128L,   129L,   130L,
                145L,   146L,   147L,   148L,   149L,   150L,
                165L,   166L,   167L,   168L,   169L,   170L,
                185L,   186L,   187L,   188L,   189L,
                205L,   206L,   207L,   208L,   209L,
                226L,   227L,   228L,   229L,
                246L,   247L,   248L,
                266L,   267L,   268L,
                287L,   288L,
                308L
                )));
        elems.add(new Elem(2L, Arrays.asList(
                184L,
                203L,   204L,
                223L,   224L,   225L,
                241L,   242L,   243L,   244L,   245L,
                260L,   261L,   262L,   263L,   264L,   265L,
                280L,   281L,   282L,   283L,   284L,   285L,   286L,
                300L,   301L,   302L,   303L,   304L,   305L,   306L,   307L,
                320L,   321L,   322L,   323L,   324L,   325L,   326L,   327L,
                340L,   341L,   342L,   343L,   344L,   345L,   346L,   347L,
                360L,   361L,   362L,   363L,   364L,   365L,   366L,   367L,
                380L,   381L,   382L,   383L,   384L,   385L,   386L,   387L

        )));
        elems.add(new Elem(3L, Arrays.asList(
                10L,    11L,    12L,    13L,    14L,    15L,    16L,    17L,
                30L,    31L,    32L,    33L,    34L,    35L,    36L,    37L,
                51L,    52L,    53L,    54L,    55L,    56L,    57L,
                71L,    72L,    73L,    74L,    75L,    76L,
                91L,    92L,    93L,    94L,    95L,    96L,
                111L,   112L,   113L,   114L,   115L,   116L,
                131L,   132L,   133L,   134L,   135L,   136L,
                151L,   152L,   153L,   154L,   155L,
                171L,   172L,   173L,   174L,   175L,
                190L,   191L,   192L,   193L,   194L,   195L,
                210L,   211L,   212L,
                230L,   231L,   232L
                )));
        elems.add(new Elem(4L, Arrays.asList(
                213L,   214L,   215L,
                233L,   234L,   235L,
                249L,   250L,   251L,   252L,   253L,   254L,   255L,
                269L,   270L,   271L,   272L,   273L,   274L,   275L,   276L,
                289L,   290L,   291L,   292L,   293L,   294L,   295L,   296L,
                309L,   310L,   311L,   312L,   313L,   314L,   315L,   316L,
                328L,   329L,   330L,   331L,   332L,   333L,   334L,   335L,   336L,   337L,
                348L,   349L,   350L,   351L,   352L,   353L,   354L,   355L,   356L,   357L,
                368L,   369L,   370L,   371L,   372L,   373L,   374L,   375L,   376L,   377L,
                388L,   389L,   390L,   391L,   392L,   393L,   394L,   395L,   396L,   397L
                )));
        elems.add(new Elem(5L, Arrays.asList(
                18L,    19L,
                38L,    39L,
                58L,    59L,
                77L,    78L,    79L,
                97L,    98L,    99L,
                117L,   118L,   119L,
                137L,   138L,   139L,
                156L,   157L,   158L,   159L,
                176L,   177L,   178L,   179L,
                196L,   197L,   198L,   199L,
                216L,   217L,   218L,   219L,
                236L,   237L,   238L,   239L,
                256L,   257L,   258L,   259L,
                277L,   278L,   279L,
                297L,   298L,   299L,
                317L,   318L,   319L,
                338L,   339L,
                358L,   359L,
                378L,   379L,
                398L,   399L
                )));
    }
}