/*
Projekt:        Vergleich der Implementierungsmöglichkeiten einer HashMap

Aufgabe:        Vergleichen Sie die Standard-HashMap des JDK11 mit einer
                alternativen Implementierung aus dem Apache Commoncs.

Dependencies im 'pom.xml' :
<dependencies>
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-core</artifactId>
            <version>${jmh.version}</version>
        </dependency>
        <dependency>
            <groupId>org.openjdk.jmh</groupId>
            <artifactId>jmh-generator-annprocess</artifactId>
            <version>${jmh.version}</version>
            <scope>provided</scope>
        </dependency>
	<dependency>
    		<groupId>org.apache.commons</groupId>
    		<artifactId>commons-collections4</artifactId>
    		<version>4.1</version>
	</dependency>

</dependencies>
 */


package com.jenkov;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.OutputTimeUnit;
//import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;
//import org.apache.commons.collections4.map.*; //"HashedMap"


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)

@State(Scope.Thread)
    public class MyBenchmark
    {
        //Erstellung der HashMap
        public HashMap<Long, Long> hmap = new HashMap<Long, Long>();
        public long key;

        //Fülle HashMap mit Zufälligen Werten
        //@Setup(Level.Iteration)
        @Benchmark
        public void setup()
        {

            key = 0;

            for(long i = 0; i < 10000; i++)
            {
                //(Erzeuge Zufälligen Wert [0-50000])
                long n = ThreadLocalRandom.current().nextLong(0, 50000);
                hmap.put(i, n);
            }
        }


        //Messung der Abrufzeit sequentiell
        @Benchmark
        public long getKeySequential() throws InterruptedException
        {

            if(key >= 10000) key=0;
            return hmap.get(key++);
        }


        //Messung der Abrufzeit chaotisch
        @Benchmark
        public long getKeyChaotic() throws InterruptedException
        {
            for(long z = 0; z < 10000; z++)
            {
                Random abrf = new Random();
                long m = abrf.nextInt(10000);
                m += 1;
                return hmap.get(m);
            }
            return hmap.get(1);
        }


        //Messung der Zeit beim Löschen von Werten
        @Benchmark
        public void delete()
        {
            for(long o = 0; o < 10000; o++)
            {
                hmap.remove(o);
            }
        }

    }