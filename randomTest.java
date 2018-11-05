package com.porfirio.orariprocida2011.activities;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.porfirio.orariprocida2011.tasks.DownloadMezziTask;
import com.porfirio.orariprocida2011.tasks.LeggiMeteoTask;
import it.unina.ptramont.utilityTest.GeneralEvent;
import it.unina.ptramont.utilityTest.SpecificUIEvent;
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class AsyncTest {
@BeforeClass
public static void startup() {
GeneralEvent.setTime(GeneralEvent.NORMAL);
}
@Test
public void RANDOM0Test() throws InterruptedException {
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.pause();
GeneralEvent.resume();
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.pause();
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.resume();
}
@Test
public void RANDOM1Test() throws InterruptedException {
GeneralEvent.startApp("com.porfirio.orariprocida2011");
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(DownloadMezziTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.pause();
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.finish(DownloadMezziTask.sem);
GeneralEvent.resume();
}
@Test
public void RANDOM2Test() throws InterruptedException {
GeneralEvent.startApp("com.porfirio.orariprocida2011");
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.pause();
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.resume();
GeneralEvent.pause();
GeneralEvent.finish(DownloadMezziTask.sem);
GeneralEvent.resume();
}
@Test
public void RANDOM3Test() throws InterruptedException {
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.pause();
GeneralEvent.resume();
GeneralEvent.finish(DownloadMezziTask.sem);
GeneralEvent.finish(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
}
@Test
public void RANDOM4Test() throws InterruptedException {
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.pause();
GeneralEvent.finish(DownloadMezziTask.sem);
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.resume();
GeneralEvent.pause();
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.finish(DownloadMezziTask.sem);
}
@Test
public void RANDOM5Test() throws InterruptedException {
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.finish(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.pause();
GeneralEvent.resume();
}
@Test
public void RANDOM6Test() throws InterruptedException {
GeneralEvent.startApp("com.porfirio.orariprocida2011");
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.pause();
GeneralEvent.start(LeggiMeteoTask.sem);
}
@Test
public void RANDOM7Test() throws InterruptedException {
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.start(DownloadMezziTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.pause();
GeneralEvent.resume();
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
}
@Test
public void RANDOM8Test() throws InterruptedException {
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.finish(DownloadMezziTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.LEGGI_METEO);
GeneralEvent.finish(LeggiMeteoTask.sem);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
SpecificUIEvent.execute(SpecificUIEvent.AGGIORNA_ORARI_DA_WEB);
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
}
@Test
public void RANDOM9Test() throws InterruptedException {
GeneralEvent.start(DownloadMezziTask.sem);
GeneralEvent.startApp("com.porfirio.orariprocida2011");
GeneralEvent.pause();
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.resume();
GeneralEvent.pause();
GeneralEvent.finish(LeggiMeteoTask.sem);
GeneralEvent.start(LeggiMeteoTask.sem);
GeneralEvent.resume();
GeneralEvent.finish(LeggiMeteoTask.sem);
}
@After
public void tearDown() {
Log.d("TEST", "End test");
}
}
