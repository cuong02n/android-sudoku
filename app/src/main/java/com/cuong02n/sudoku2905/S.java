package com.cuong02n.sudoku2905;

public interface S {

    void generated_Sudoku();

    void generated_adView();

    void set_undo_button();

    void set_remove_button();

    void set_button_click_listener();

    void set_text_click_n_change_listener();

    void find_TextView();

    void set_note_button();

    void set_hint_button();

    void set_back_button();

    void set_clear_note_button();

    void set_note_all_button();

    void set_clear_button();

    void showRewardedAd();

    void loadAdReward();

    void b1();

    boolean checkfree(int u, int i, int j);

    void solution();

    void solveSudoku(int i, int j);

    String printtime(long time);

    void updateText();

    void complete1();

    void setnoteAll();

    int makenumber(char m);

    char makechar(int m);

    void sethack();

    void putundo(int m);

    void setundo();

    void setting();
}
