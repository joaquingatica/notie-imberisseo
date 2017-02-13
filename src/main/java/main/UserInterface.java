/**
 * Notië Imberissëo
 * <p>
 * Copyright 2012 Joaquín Gatica (Erutulco Eruntano)
 * <p>
 * Contact:
 * Twitter: <http://twitter.com/joaquingatica>
 * Email: <erutulco@quenya101.com>
 * <p>
 * This file is part of "Notië Imberissëo".
 * <p>
 * "Notië Imberissëo" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * "Notië Imberissëo" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with "Notië Imberissëo".  If not, see <http://www.gnu.org/licenses/>.
 */

package main;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import data.GregorianInfo;
import data.ImladrisInfo;
import fonts.FontManager;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import lang.Lang;
import lang.LangManager;

public class UserInterface implements HyperlinkListener {

  /* FRAME */
  private JFrame frmNotiImberisso;
  private JTabbedPane tabbedPane;

  /* GREGORIAN to IMLADRIS */
  private JTextField year;
  private JComboBox month;
  private JComboBox day;
  private JCheckBox afterSunset;
  private JButton toImladris;
  private JTextPane resImladris;

  /* IMLADRIS to GREGORIAN */
  private JTextField loa;
  private JComboBox yen;
  private JComboBox period;
  private JComboBox dayOfLoa;
  private JCheckBox beforeMidnight;
  private JButton toGregorian;
  private JTextPane resGregorian;

  /* SETTINGS */
  private JTextField city;
  private JTextField country;
  private JComboBox langCombo;
  private JLabel saveResult;

  /* ABOUT */
  private JEditorPane aboutEditor;

  /* TODAY */
  private JLabel locationInfo;
  private JTextPane todayGregorian;
  private JTextPane todayImladris;

  /* Frame */
  public JFrame getFrame() {
    return frmNotiImberisso;
  }

  public void setFrame(JFrame frame) {
    this.frmNotiImberisso = frame;
  }

  public JTabbedPane getTabbedPane() {
    return tabbedPane;
  }

  public void setTabbedPane(JTabbedPane tabbedPane) {
    this.tabbedPane = tabbedPane;
  }

  /* Gregorian to Imladris */
  public JTextField getYear() {
    return year;
  }

  public void setYear(JTextField year) {
    this.year = year;
  }

  public JComboBox getMonth() {
    return month;
  }

  public void setMonth(JComboBox month) {
    this.month = month;
  }

  public JComboBox getDay() {
    return day;
  }

  public void setDay(JComboBox day) {
    this.day = day;
  }

  public JCheckBox getAfterSunset() {
    return afterSunset;
  }

  public void setAfterSunset(JCheckBox afterSunset) {
    this.afterSunset = afterSunset;
  }

  public JButton getToImladris() {
    return toImladris;
  }

  public void setToImladris(JButton toImladris) {
    this.toImladris = toImladris;
  }

  public JTextPane getResImladris() {
    return resImladris;
  }

  public void setResImladris(JTextPane resImladris) {
    this.resImladris = resImladris;
  }

  /* Imladris to Gregorian */
  public JTextField getLoa() {
    return loa;
  }

  public void setLoa(JTextField loa) {
    this.loa = loa;
  }

  public JComboBox getYen() {
    return yen;
  }

  public void setYen(JComboBox yen) {
    this.yen = yen;
  }

  public JComboBox getPeriod() {
    return period;
  }

  public void setPeriod(JComboBox period) {
    this.period = period;
  }

  public JComboBox getDayOfLoa() {
    return dayOfLoa;
  }

  public void setDayOfLoa(JComboBox dayOfLoa) {
    this.dayOfLoa = dayOfLoa;
  }

  public JCheckBox getBeforeMidnight() {
    return beforeMidnight;
  }

  public void setBeforeMidnight(JCheckBox beforeMidnight) {
    this.beforeMidnight = beforeMidnight;
  }

  public JButton getToGregorian() {
    return toGregorian;
  }

  public void setToGregorian(JButton toGregorian) {
    this.toGregorian = toGregorian;
  }

  public JTextPane getResGregorian() {
    return resGregorian;
  }

  public void setResGregorian(JTextPane resGregorian) {
    this.resGregorian = resGregorian;
  }

  /* Settings */
  public JTextField getCity() {
    return city;
  }

  public void setCity(JTextField city) {
    this.city = city;
  }

  public JTextField getCountry() {
    return country;
  }

  public void setCountry(JTextField country) {
    this.country = country;
  }

  public JComboBox getLangCombo() {
    return langCombo;
  }

  public void setLangCombo(JComboBox langCombo) {
    this.langCombo = langCombo;
  }

  public JLabel getSaveResult() {
    return saveResult;
  }

  public void setSaveResult(JLabel saveResult) {
    this.saveResult = saveResult;
  }

  /* Today */
  public JLabel getLocationInfo() {
    return locationInfo;
  }

  public void setLocationInfo(JLabel locationInfo) {
    this.locationInfo = locationInfo;
  }

  public JTextPane getTodayGregorian() {
    return todayGregorian;
  }

  public void setTodayGregorian(JTextPane todayGregorian) {
    this.todayGregorian = todayGregorian;
  }

  public JTextPane getTodayImladris() {
    return todayImladris;
  }

  public void setTodayImladris(JTextPane todayImladris) {
    this.todayImladris = todayImladris;
  }

  /**
   * Create the application.
   */
  private static UserInterface instance = null;

  /**
   * Singleton method.
   * @return Singleton instance
   */
  public static UserInterface getInstance() {
    if (instance == null) {
      instance = new UserInterface();
    }
    return instance;
  }

  public static void disposeInstance() {
    instance = null;
  }

  private UserInterface() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    /* Set system Look & Feel */
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (UnsupportedLookAndFeelException e) {
      System.err.println(e.getMessage());
    } catch (ClassNotFoundException e) {
      System.err.println(e.getMessage());
    } catch (InstantiationException e) {
      System.err.println(e.getMessage());
    } catch (IllegalAccessException e) {
      System.err.println(e.getMessage());
    }

    /* Register fonts */
    FontManager.registerAvailableFonts();

    frmNotiImberisso = new JFrame();
    frmNotiImberisso.setTitle(Lang.Common.app_title);
    frmNotiImberisso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frmNotiImberisso.setSize(600, 380);
    frmNotiImberisso.setLocation(300, 200);
    /* Get app icon */
    try {
      InputStream is = new BufferedInputStream(
          this.getClass().getResourceAsStream("/images/icon.png")
      );
      Image icon = ImageIO.read(is);
      frmNotiImberisso.setIconImage(icon);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
    if (Lang.uses_tengwar) {
      frmNotiImberisso.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }

    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWidths = new int[]{520, 0};
    gridBagLayout.rowHeights = new int[]{227, 0, 0};
    gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
    gridBagLayout.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
    frmNotiImberisso.getContentPane().setLayout(gridBagLayout);

    tabbedPane = new JTabbedPane(JTabbedPane.TOP);
    GridBagConstraints gbcTabbedPane = new GridBagConstraints();
    gbcTabbedPane.insets = new Insets(0, 0, 5, 0);
    gbcTabbedPane.fill = GridBagConstraints.BOTH;
    gbcTabbedPane.gridx = 0;
    gbcTabbedPane.gridy = 0;
    frmNotiImberisso.getContentPane().add(tabbedPane, gbcTabbedPane);

    JPanel panel1 = new JPanel();
    tabbedPane.addTab(Lang.ToImladrisTab.title, null, panel1, Lang.ToImladrisTab.tooltip);
    if (Lang.uses_tengwar) {
      tabbedPane.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.setBorder(null);
    panel1.setLayout(new FormLayout(new ColumnSpec[]{
        ColumnSpec.decode("max(2dlu;default)"),
        ColumnSpec.decode("172px"),
        ColumnSpec.decode("32px"),
        FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
        ColumnSpec.decode("55px"),
        FormSpecs.LABEL_COMPONENT_GAP_COLSPEC,
        ColumnSpec.decode("32px"),
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(20dlu;default):grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("default:grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,},
        new RowSpec[]{
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("max(14dlu;default)"),
            FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("max(5dlu;default)"),
            FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("max(12dlu;default)"),
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,}));

    JLabel lblYear = new JLabel(Lang.ToImladrisTab.year_label);
    lblYear.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblYear.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblYear, "5, 2, 3, 1, center, default");

    JLabel lblMonth = new JLabel(Lang.ToImladrisTab.month_label);
    lblMonth.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblMonth.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblMonth, "9, 2, 5, 1, center, default");

    JLabel lblDay = new JLabel(Lang.ToImladrisTab.day_label);
    lblDay.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblDay.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblDay, "15, 2, 3, 1, center, default");

    JLabel lblChooseGregorianDate = new JLabel(Lang.ToImladrisTab.choose_label);
    if (Lang.uses_tengwar) {
      lblChooseGregorianDate.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblChooseGregorianDate, "2, 4, 2, 1, fill, fill");

    year = new JTextField();
    year.setToolTipText(Lang.ToImladrisTab.year_tooltip);
    year.addFocusListener(new FocusAdapter() {
      public void focusLost(FocusEvent e) {
        UserInterfaceController.getInstance().updateDayComboGregorian();
      }
    });
    if (Lang.uses_tengwar) {
      year.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(year, "5, 4, 3, 1, fill, fill");

    month = new JComboBox();
    month.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        UserInterfaceController.getInstance().updateDayComboGregorian();
      }
    });
    month.setModel(new DefaultComboBoxModel(GregorianInfo.getInstance().getMonthsArray()));
    panel1.add(month, "9, 4, 5, 1, fill, fill");

    day = new JComboBox();
    day.setEnabled(false);
    panel1.add(day, "15, 4, 3, 1, fill, fill");

    toImladris = new JButton(">>");
    toImladris.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        UserInterfaceController.getInstance().convertToImladris();
      }
    });
    toImladris.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    toImladris.setEnabled(false);
    panel1.add(toImladris, "11, 6, 7, 1, fill, fill");

    afterSunset = new JCheckBox(Lang.ToImladrisTab.after_sunset_label);
    afterSunset.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      afterSunset.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    afterSunset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    panel1.add(afterSunset, "5, 6, 5, 1");

    JLabel lblResultingImladrisDate = new JLabel(Lang.ToImladrisTab.resulting_label);
    if (Lang.uses_tengwar) {
      lblResultingImladrisDate.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblResultingImladrisDate, "2, 10, 2, 1, fill, fill");

    resImladris = new JTextPane();
    resImladris.setEditable(false);
    resImladris.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    panel1.add(resImladris, "5, 10, 13, 1, fill, fill");

    JLabel lblFormat = new JLabel(Lang.Punctuation.parenthesis_open
        + Lang.ToImladrisTab.resulting_format + Lang.Punctuation.parenthesis_close);
    lblFormat.setFont(new Font("Dialog", Font.PLAIN, 10));
    if (Lang.uses_tengwar) {
      lblFormat.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel1.add(lblFormat, "5, 12, 13, 1, fill, fill");

    JPanel panel2 = new JPanel();
    tabbedPane.addTab(Lang.FromImladrisTab.title, null, panel2, Lang.FromImladrisTab.tooltip);
    panel2.setBorder(null);
    panel2.setLayout(new FormLayout(new ColumnSpec[]{
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(87dlu;default)"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("left:max(13dlu;default)"),
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(12dlu;default)"),
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(8dlu;default):grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(9dlu;default):grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("default:grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,},
        new RowSpec[]{
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("max(5dlu;default)"),
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,}));

    JLabel lblYn = new JLabel(Lang.FromImladrisTab.yen_label);
    lblYn.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblYn.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblYn, "6, 2, 3, 1, center, default");

    JLabel lblLoa = new JLabel(Lang.FromImladrisTab.loa_label);
    lblLoa.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblLoa.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblLoa, "10, 2, 3, 1, center, default");

    JLabel lblPeriod = new JLabel(Lang.FromImladrisTab.period_label);
    lblPeriod.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblPeriod.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblPeriod, "14, 2, 5, 1, center, default");

    JLabel lblNewLabel = new JLabel(Lang.FromImladrisTab.day_label);
    lblNewLabel.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblNewLabel.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblNewLabel, "20, 2, 3, 1, center, default");

    JLabel lblS = new JLabel(Lang.FromImladrisTab.choose_label);
    if (Lang.uses_tengwar) {
      lblS.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblS, "2, 4, 3, 1, fill, fill");

    yen = new JComboBox();
    yen.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        UserInterfaceController.getInstance().updateDayComboImladris();
      }
    });
    yen.setModel(new DefaultComboBoxModel(ImladrisInfo.getInstance().getYeniArray()));
    panel2.add(yen, "6, 4, 3, 1, fill, fill");

    loa = new JTextField();
    loa.addFocusListener(new FocusAdapter() {
      @Override
      public void focusLost(FocusEvent e) {
        UserInterfaceController.getInstance().updateDayComboImladris();
      }
    });
    loa.setToolTipText(Lang.FromImladrisTab.loa_tooltip);
    panel2.add(loa, "10, 4, 3, 1, fill, fill");
    loa.setColumns(10);

    period = new JComboBox();
    period.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        UserInterfaceController.getInstance().updateDayComboImladris();
      }
    });
    period.setModel(new DefaultComboBoxModel(ImladrisInfo.getInstance().getPeriodsArray()));
    panel2.add(period, "14, 4, 5, 1, fill, fill");

    dayOfLoa = new JComboBox();
    dayOfLoa.setEnabled(false);
    panel2.add(dayOfLoa, "20, 4, 3, 1, fill, fill");

    toGregorian = new JButton(">>");
    toGregorian.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        UserInterfaceController.getInstance().convertToGregorian();
      }
    });
    toGregorian.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    toGregorian.setEnabled(false);
    panel2.add(toGregorian, "16, 6, 7, 1, fill, fill");

    beforeMidnight = new JCheckBox(Lang.FromImladrisTab.before_midnight_label);
    beforeMidnight.setFont(new Font("Dialog", Font.PLAIN, 12));
    beforeMidnight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    panel2.add(beforeMidnight, "6, 6, 9, 1, fill, fill");

    JLabel lblNewLabel1 = new JLabel(Lang.FromImladrisTab.resulting_label);
    panel2.add(lblNewLabel1, "2, 10, 3, 1, fill, fill");

    resGregorian = new JTextPane();
    resGregorian.setEditable(false);
    resGregorian.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    panel2.add(resGregorian, "6, 10, 17, 1, fill, fill");

    JLabel lblNewLabel2 = new JLabel(Lang.Punctuation.parenthesis_open
        + Lang.FromImladrisTab.resulting_format + Lang.Punctuation.parenthesis_close);
    lblNewLabel2.setFont(new Font("Dialog", Font.PLAIN, 10));
    if (Lang.uses_tengwar) {
      lblNewLabel2.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel2.add(lblNewLabel2, "6, 12, 17, 1");

    JPanel panel = new JPanel();
    panel.setToolTipText("");
    tabbedPane.addTab(Lang.SettingsTab.title, null, panel, Lang.SettingsTab.tooltip);
    panel.setLayout(new FormLayout(new ColumnSpec[]{
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(61dlu;default):grow"),
        FormSpecs.RELATED_GAP_COLSPEC,
        FormSpecs.DEFAULT_COLSPEC,
        FormSpecs.RELATED_GAP_COLSPEC,
        ColumnSpec.decode("max(112dlu;default)"),},
        new RowSpec[]{
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            RowSpec.decode("max(16dlu;default):grow"),
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,
            FormSpecs.RELATED_GAP_ROWSPEC,
            FormSpecs.DEFAULT_ROWSPEC,}));

    JLabel lblLocation = new JLabel(Lang.SettingsTab.location);
    if (Lang.uses_tengwar) {
      lblLocation.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel.add(lblLocation, "4, 2, center, center");

    JLabel lblCity = new JLabel(Lang.SettingsTab.city_label + Lang.Punctuation.double_dot);
    if (Lang.uses_tengwar) {
      lblCity.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel.add(lblCity, "2, 4, right, fill");

    city = new JTextField();
    panel.add(city, "4, 4, fill, fill");
    city.setColumns(10);

    JLabel lblCountry = new JLabel(Lang.SettingsTab.country_label + Lang.Punctuation.double_dot);
    if (Lang.uses_tengwar) {
      lblCountry.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel.add(lblCountry, "2, 6, right, fill");

    country = new JTextField();
    panel.add(country, "4, 6, fill, fill");
    country.setColumns(10);

    JLabel lblLanguage = new JLabel(Lang.SettingsTab.language_label + Lang.Punctuation.double_dot);
    if (Lang.uses_tengwar) {
      lblLanguage.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel.add(lblLanguage, "2, 10, right, default");

    langCombo = new JComboBox();
    langCombo.setModel(new DefaultComboBoxModel(LangManager.getLanguagesPrintable()));
    panel.add(langCombo, "4, 10, fill, default");

    saveResult = new JLabel("");
    saveResult.setForeground(Color.GREEN);
    if (Lang.uses_tengwar) {
      saveResult.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel.add(saveResult, "2, 12, 3, 1, right, fill");

    JButton btnSave = new JButton(Lang.SettingsTab.save);
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        UserInterfaceController.getInstance().saveSettings();
      }
    });
    if (Lang.uses_tengwar) {
      btnSave.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    panel.add(btnSave, "4, 14, right, fill");

    JPanel panel4 = new JPanel();
    tabbedPane.addTab(Lang.AboutTab.title, null, panel4, null);
    GridBagLayout gblPanel4 = new GridBagLayout();
    gblPanel4.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    gblPanel4.rowHeights = new int[]{23, 0, 0, 0};
    gblPanel4.columnWeights = new double[]{
        0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
        0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE
    };
    gblPanel4.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
    panel4.setLayout(gblPanel4);

    JLabel lblNotiImberisso = new JLabel(Lang.AboutTab.app_name + " " + Lang.Common.app_version);
    lblNotiImberisso.setFont(new Font("Dialog", Font.BOLD, 12));
    if (Lang.uses_tengwar) {
      lblNotiImberisso.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    GridBagConstraints gbcLblNotiImberisso = new GridBagConstraints();
    gbcLblNotiImberisso.anchor = GridBagConstraints.SOUTH;
    gbcLblNotiImberisso.gridwidth = 19;
    gbcLblNotiImberisso.insets = new Insets(0, 0, 5, 0);
    gbcLblNotiImberisso.gridx = 0;
    gbcLblNotiImberisso.gridy = 0;
    panel4.add(lblNotiImberisso, gbcLblNotiImberisso);

    JLabel lbltvbwtrn = new JLabel(Lang.AboutTab.tengwar_name);
    Font tengwarFont = FontManager.getFont("tngan.ttf", "Tengwar Annatar", Font.BOLD, 14);
    lbltvbwtrn.setFont(tengwarFont);
    GridBagConstraints gbcLbltvbwtrn = new GridBagConstraints();
    gbcLbltvbwtrn.gridwidth = 19;
    gbcLbltvbwtrn.insets = new Insets(0, 0, 5, 0);
    gbcLbltvbwtrn.gridx = 0;
    gbcLbltvbwtrn.gridy = 1;
    panel4.add(lbltvbwtrn, gbcLbltvbwtrn);

    aboutEditor = new JEditorPane();
    aboutEditor.setContentType("text/html");
    aboutEditor.setEditable(false);
    aboutEditor.setText(Lang.AboutTab.info);
    aboutEditor.addHyperlinkListener(this);
    if (Lang.uses_tengwar) {
      aboutEditor.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    GridBagConstraints gbcDtrpnF = new GridBagConstraints();
    gbcDtrpnF.gridwidth = 19;
    gbcDtrpnF.insets = new Insets(0, 0, 0, 5);
    gbcDtrpnF.fill = GridBagConstraints.BOTH;
    gbcDtrpnF.gridx = 0;
    gbcDtrpnF.gridy = 2;
    panel4.add(aboutEditor, gbcDtrpnF);

    GridBagConstraints gbcPanel3 = new GridBagConstraints();
    gbcPanel3.fill = GridBagConstraints.BOTH;
    gbcPanel3.gridx = 0;
    gbcPanel3.gridy = 1;
    JPanel panel3 = new JPanel();
    frmNotiImberisso.getContentPane().add(panel3, gbcPanel3);
    GridBagLayout gblPanel3 = new GridBagLayout();
    gblPanel3.columnWidths = new int[]{0, 460, 0};
    gblPanel3.rowHeights = new int[]{0, 24, 0, 0};
    gblPanel3.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
    gblPanel3.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
    panel3.setLayout(gblPanel3);

    JLabel lblTodaysDate = new JLabel(Lang.Common.current_date_label);
    GridBagConstraints gbcLblTodaysDate = new GridBagConstraints();
    gbcLblTodaysDate.insets = new Insets(0, 0, 5, 5);
    gbcLblTodaysDate.gridx = 0;
    gbcLblTodaysDate.gridy = 0;
    if (Lang.uses_tengwar) {
      lblTodaysDate.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    panel3.add(lblTodaysDate, gbcLblTodaysDate);

    locationInfo = new JLabel("");
    locationInfo.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      locationInfo.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    GridBagConstraints gbcLocationInfo = new GridBagConstraints();
    gbcLocationInfo.anchor = GridBagConstraints.EAST;
    gbcLocationInfo.insets = new Insets(0, 0, 5, 0);
    gbcLocationInfo.gridx = 1;
    gbcLocationInfo.gridy = 0;
    panel3.add(locationInfo, gbcLocationInfo);

    JLabel lblGregorian = new JLabel(Lang.Common.gregorian_label);
    lblGregorian.setFont(new Font("Dialog", Font.PLAIN, 12));
    GridBagConstraints gbcLblGregorian = new GridBagConstraints();
    gbcLblGregorian.anchor = GridBagConstraints.EAST;
    gbcLblGregorian.insets = new Insets(0, 0, 5, 5);
    gbcLblGregorian.gridx = 0;
    gbcLblGregorian.gridy = 1;
    panel3.add(lblGregorian, gbcLblGregorian);

    todayGregorian = new JTextPane();
    todayGregorian.setEditable(false);
    todayGregorian.setFont(new Font("Dialog", Font.BOLD, 12));
    if (Lang.uses_tengwar) {
      todayGregorian.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    todayGregorian.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    GridBagConstraints gbcTodayGregorian = new GridBagConstraints();
    gbcTodayGregorian.insets = new Insets(0, 0, 5, 0);
    gbcTodayGregorian.fill = GridBagConstraints.BOTH;
    gbcTodayGregorian.gridx = 1;
    gbcTodayGregorian.gridy = 1;
    panel3.add(todayGregorian, gbcTodayGregorian);

    JLabel lblImladris = new JLabel(Lang.Common.imladris_label);
    lblImladris.setFont(new Font("Dialog", Font.PLAIN, 12));
    if (Lang.uses_tengwar) {
      lblImladris.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    GridBagConstraints gbcLblImladris = new GridBagConstraints();
    gbcLblImladris.anchor = GridBagConstraints.EAST;
    gbcLblImladris.insets = new Insets(0, 0, 0, 5);
    gbcLblImladris.gridx = 0;
    gbcLblImladris.gridy = 2;
    panel3.add(lblImladris, gbcLblImladris);

    todayImladris = new JTextPane();
    todayImladris.setEditable(false);
    todayImladris.setFont(new Font("Dialog", Font.BOLD, 12));
    if (Lang.uses_tengwar) {
      todayImladris.setFont(FontManager.getTrueTypeFont("tngan.ttf"));
    }
    todayImladris.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
    GridBagConstraints gbcTodayImladris = new GridBagConstraints();
    gbcTodayImladris.fill = GridBagConstraints.BOTH;
    gbcTodayImladris.gridx = 1;
    gbcTodayImladris.gridy = 2;
    panel3.add(todayImladris, gbcTodayImladris);
  }


  @Override
  public void hyperlinkUpdate(HyperlinkEvent event) {
    if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
      if (Desktop.isDesktopSupported()) {
        Desktop desktop = Desktop.getDesktop();
        try {
          desktop.browse(event.getURL().toURI());
        } catch (IOException e) {
          e.printStackTrace();
        } catch (URISyntaxException e) {
          e.printStackTrace();
        }
      }
    }
  }

}
