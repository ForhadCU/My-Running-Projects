package com.example.soccertournamethelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/*class DbHelper {
    private DbHelper dbHelper;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;


    DbHelper(Context context) {
        this.context = context;
    }

    //open sqLiteDatabase
    com.example.soccertournamethelper.DbHelper openDB() throws SQLException{
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        return this;
    }
    //close sqLiteDatabase
    public void closeDB()
    {
        dbHelper.close();
    }*/



   /*//Work for me (MyTag: 1111)
   String selectMethod()
    {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
        String[] columns = {DbHelper.dTournamentName,DbHelper.dTeamNumber, DbHelper.dWinnerPoint, DbHelper.dDrawPoint};

        Cursor cursor = sqLiteDatabase.query(DbHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext())
        {
            int index1 = cursor.getColumnIndex(DbHelper.dTournamentName);
            int index2 = cursor.getColumnIndex(DbHelper.dTeamNumber);
            int index3 = cursor.getColumnIndex(DbHelper.dWinnerPoint);
            int index4 = cursor.getColumnIndex(DbHelper.dDrawPoint);

            String vTName = cursor.getString(index1);
            String vTNum = cursor.getString(index2);
            String vWP = cursor.getString(index3);
            String vDP = cursor.getString(index4);

            stringBuffer.append(vTName +" "+ vTNum +" "+ vWP+ " "+ vDP+ '\n');
        }
        return stringBuffer.toString();
    }*/

  /* Cursor selectMethod()
   {
        String[] colounm = new String[]{com.example.soccertournamethelper.DbHelper.DbHelper.dID, com.example.soccertournamethelper.DbHelper.DbHelper.dTourUID, com.example.soccertournamethelper.DbHelper.DbHelper.dTournamentName, com.example.soccertournamethelper.DbHelper.DbHelper.dTeamNumber};
        Cursor cursor = sqLiteDatabase.query(com.example.soccertournamethelper.DbHelper.DbHelper.TABLE_NAME, colounm, null, null, null, null, null);

        if (cursor != null)
        {
            cursor.moveToFirst();
        }
        return cursor;
   }


    long mCreateTournament(String tourUID, String tournamentName, String teamNumber, String winnerPoint, String drawPoint) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(com.example.soccertournamethelper.DbHelper.DbHelper.dTourUID, tourUID);
        contentValues.put(com.example.soccertournamethelper.DbHelper.DbHelper.dTournamentName, tournamentName);
        contentValues.put(com.example.soccertournamethelper.DbHelper.DbHelper.dTeamNumber, teamNumber);
        contentValues.put(com.example.soccertournamethelper.DbHelper.DbHelper.dWinnerPoint, winnerPoint);
        contentValues.put(com.example.soccertournamethelper.DbHelper.DbHelper.dDrawPoint, drawPoint);

        long id = sqLiteDatabase.insert(com.example.soccertournamethelper.DbHelper.DbHelper.TABLE_NAME, null, contentValues);
        return id;
    }*/
/*
    long mAddTeams(String tourUID, String teamNames, int mp, int w, int d, int l, int gf, int ga, int gd, int pts)
    {
        ContentValues contentValues1 = new ContentValues();

        contentValues1.put(DbHelper.dTourUID, tourUID);
        contentValues1.put(DbHelper.dTeamNames, teamNames);
        contentValues1.put(DbHelper.dMatch, mp);
        contentValues1.put(DbHelper.dWin, w);
        contentValues1.put(DbHelper.dDraw, d);
        contentValues1.put(DbHelper.dLoss, l);
        contentValues1.put(DbHelper.dGoalFOr, gf);
        contentValues1.put(DbHelper.dGoalAgainst, ga);
        contentValues1.put(DbHelper.dGoalDiff, gd);
        contentValues1.put(DbHelper.dPoints, pts);

        long id1 = sqLiteDatabase.insert(DbHelper.TABLE_NAME_1, null, contentValues1);
        return id1;
    }
*/


   /* long mAddTeams(String tourUID, String teamNames)
    {
        SQLiteDatabase sd = dbHelper.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();

        contentValues1.put(com.example.soccertournamethelper.DbHelper.DbHelper.dTourUID, tourUID);
        contentValues1.put(com.example.soccertournamethelper.DbHelper.DbHelper.dTeamNames, teamNames);

        long id1 = sd.insert(com.example.soccertournamethelper.DbHelper.DbHelper.TABLE_NAME_1, null, contentValues1);
        return id1;
    }*/

    public  class DbHelper extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "SoccerTH_DB";
        private static final String TABLE_NAME = "Tournament_list_table";
        private static final String TABLE_NAME_1 = "Team_list_table";
        private static final int DATABASE_VERSION = 8;
        public static  final String dID = "_id";
//        public static  final String dID1 = "_id_1";
        public static final String dTourUID = "Tour_id";
        public static final String dTournamentName = "Tour_name";
        public static final String dTeamNumber = "Team_number";
        public static final String dWinnerPoint = "Win_point";
        public static final String dDrawPoint = "Draw_Point";
        public static final String dTeamNames = "Team";
        public static final String dMatch = "MP";
        public static final String dWin = "W";
        public static final String dDraw = "D";
        public static final String dLoss = "L";
        public static final String dGoalFOr = "GF";
        public static final String dGoalAgainst = "GA";
        public static final String dGoalDiff = "GD";
        public static final String dPoints = "Pts";

        private static final String dCREATE_TABLE = "CREATE TABLE "+TABLE_NAME+" ("+dID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + dTourUID + " TEXT  NOT NULL, " +
                "" + dTournamentName + " VARCHAR(255),"+ dTeamNumber +" INTEGER, " + dWinnerPoint + " INTEGER, " + dDrawPoint + " INTEGER);";

        private static final String dCREATE_TABLE_1 = "CREATE TABLE "+TABLE_NAME_1+" ("+dID+" INTEGER PRIMARY KEY AUTOINCREMENT , "+dTourUID+" VARCHAR(5), "+dTeamNames+" VARCHAR(30)" +
                ", "+dMatch+" INTEGER DEFAULT 0, "+dWin+" INTEGER DEFAULT 0, "+dDraw+" INTEGER DEFAULT 0, "+dLoss+" INTEGER DEFAULT 0, "+dGoalFOr+" INTEGER DEFAULT 0, " +
                ""+dGoalAgainst+" INTEGER DEFAULT 0, "+dGoalDiff+" INTEGER DEFAULT 0, "+dPoints+" INTEGER DEFAULT 0);";

        DbHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(dCREATE_TABLE);
                db.execSQL(dCREATE_TABLE_1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
                db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_1);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Cursor mReadPoints(String tourID)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] column = new String[]{dWinnerPoint, dDrawPoint};
            String[] selectionArgs = new String[]{tourID};

            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, column, dTourUID+ " =? ", selectionArgs, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();
            return cursor;
        }

        Cursor mReadSavedColumn(String tourID, String teamName)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] column = new String[] {dMatch, dWin, dDraw, dLoss, dGoalFOr, dGoalAgainst, dGoalDiff, dPoints};
            String[] selectionArgs = new String[]{tourID, teamName};
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, column, dTourUID+" =? AND "+ dTeamNames+" =? ", selectionArgs, null, null, null);
            if (cursor != null)
            {
                cursor.moveToFirst();
            }
            return cursor;
        }


        int mUpdatePointsTable(String tourId, String teamName, int mp, int win, int draw, int loss, int gf, int ga, int gd, int pts)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(dMatch, mp);
            contentValues.put(dWin, win);
            contentValues.put(dDraw, draw);
            contentValues.put(dLoss, loss);
            contentValues.put(dGoalFOr, gf);
            contentValues.put(dGoalAgainst, ga);
            contentValues.put(dGoalDiff, gd);
            contentValues.put(dPoints, pts);

            String[] selectedArgs = new String[]{tourId, teamName};
            int count = sqLiteDatabase.update(TABLE_NAME_1, contentValues, dTourUID+ " =? AND "+ dTeamNames+ " =? ", selectedArgs);
            return count;
        }


        long mCreateTournament(String tourUID, String tournamentName, String teamNumber, String winnerPoint, String drawPoint) {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();

            contentValues.put(dTourUID, tourUID);
            contentValues.put(dTournamentName, tournamentName);
            contentValues.put(dTeamNumber, teamNumber);
            contentValues.put(dWinnerPoint, winnerPoint);
            contentValues.put(dDrawPoint, drawPoint);

            long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
            return id;
        }


        Cursor selectMethod()
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] colounm = new String[]{dID, dTourUID, dTournamentName, dTeamNumber};
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME, colounm, null, null, null, null, null);

            if (cursor != null) {
                cursor.moveToFirst();
            }
            return cursor;
        }

        Cursor mSelectAllResult(String tourID)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] columns = new String[]{dID, dTeamNames, dMatch, dWin, dDraw, dLoss, dGoalFOr, dGoalAgainst, dGoalDiff, dPoints};
            String[] selectionArgs = new String[]{tourID};
            /*Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, columns, dTourUID+" =? ", selectionArgs, null,
                    null, dPoints+" DESC, "+ dGoalDiff+" DESC", null);*/
            String sQuery = "SELECT _id, Team, MP, W, D, L, GF, GA, GD, Pts FROM Team_list_table WHERE Tour_id = ? ORDER BY Pts DESC, GD DESC";

            Cursor cursor = sqLiteDatabase.rawQuery(sQuery, selectionArgs);
            /*if (cursor != null) {
                cursor.moveToFirst();
            }*/
            return cursor;
        }

       public ArrayList<Data_handler> mListData(String tourID)
        {
            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            ArrayList<Data_handler> storeData = new ArrayList<>();

            String[] selectionArgs = new String[]{tourID};
            String sQuery = "SELECT Team, MP, W, D, L, GF, GA, GD, Pts FROM Team_list_table WHERE Tour_id = ? ORDER BY Pts DESC, GD DESC";
            Cursor cursor = sqLiteDatabase.rawQuery(sQuery, selectionArgs);
            int count = 1;
            if (cursor.moveToFirst())
            {
                do {
                    int index1 = cursor.getColumnIndex(DbHelper.dTeamNames);
                    int index2 = cursor.getColumnIndex(DbHelper.dWin);
                    int index3 = cursor.getColumnIndex(DbHelper.dDraw);
                    int index4 = cursor.getColumnIndex(DbHelper.dLoss);
                    int index5 = cursor.getColumnIndex(DbHelper.dGoalFOr);
                    int index6 = cursor.getColumnIndex(DbHelper.dGoalAgainst);
                    int index7 = cursor.getColumnIndex(DbHelper.dGoalDiff);
                    int index8 = cursor.getColumnIndex(DbHelper.dPoints);
                    int index9 = cursor.getColumnIndex(DbHelper.dMatch);
                    String teamName = cursor.getString(index1);
                    String preWin = cursor.getString(index2);
                    String preDraw = cursor.getString(index3);
                    String preLoss = cursor.getString(index4);
                    String preGoalFor = cursor.getString(index5);
                    String preGoalAgainst = cursor.getString(index6);
                    String prePoints = cursor.getString(index8);
                    String match = cursor.getString(index9);
                    String goalDiff = cursor.getString(index7);
                    int i = count++;
                    String rank = Integer.toString(i);

                    storeData.add(new Data_handler( rank,  teamName,  match, preWin, preDraw, preLoss, preGoalFor, preGoalAgainst, goalDiff, prePoints));
                }while (cursor.moveToNext());
            }
            cursor.close();
            return storeData;
        }


    /*    ArrayList<String> dataList = new ArrayList<>();
        ArrayList<String> mShowPointTable(String tourID)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] columns = new String[]{dID, dTeamNames, dMatch, dWin, dDraw, dLoss, dGoalFOr, dGoalAgainst, dGoalDiff, dPoints};
            String[] selectionArgs = new String[]{tourID};
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, columns, dTourUID+" =? ", selectionArgs, null, null, null);

//            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME_1+" WHERE dTourUID =?", selectionArgs);

            while (cursor.moveToNext())
            {

            }
        }*/


        long mAddTeams(String tourUID, String teamNames)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            ContentValues contentValues1 = new ContentValues();

            contentValues1.put(dTourUID, tourUID);
            contentValues1.put(dTeamNames, teamNames);

            long id1 = sqLiteDatabase.insert(DbHelper.TABLE_NAME_1, null, contentValues1);
            return id1;
        }
        //select all Team names by tourID


        Cursor mShowTeamList(String tourID)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String[] column = new String[]{dID, dTeamNames};
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, column, dTourUID+" = '"+tourID+"'", null, null,
                    null, null);
           /* if (cursor.moveToFirst())
            {
                do
                {
                    int index1 = cursor.getColumnIndex(dID1);
                    int index2 = cursor.getColumnIndex(dTeamNames);
                    String cID1 = cursor.getString(index1);
                    String cTeamName = cursor.getString(index2);

                    teamList.add(cID1);
                    teamList.add(cTeamName);
                }while (cursor.moveToNext());
            }
            return teamList;*/
           if (cursor != null)
           {
               cursor.moveToFirst();
           }
           return cursor;

        }


        ArrayList<String> teamList = new ArrayList<String>();
        ArrayList<String> mShowTeamArrayList(String tourId)
        {
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            String[] coloumn = new String[]{dTeamNames};
            Cursor cursor = sqLiteDatabase.query(TABLE_NAME_1, coloumn, dTourUID+ " = '"+tourId+"'", null, null, null, null);

            while (cursor.moveToNext()){
                String cTeamName = cursor.getString(cursor.getColumnIndex(dTeamNames));
                teamList.add(cTeamName);
        }
            return teamList;
        }

    }





//}

/*    long mAddTeams(String tourUID, String teamNames, int mp, int w, int d, int l, int gf, int ga, int gd, int pts)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();

        contentValues1.put(DbHelper.dTourUID, tourUID);
        contentValues1.put(DbHelper.dTeamNames, teamNames);
        contentValues1.put(DbHelper.dMatch, mp);
        contentValues1.put(DbHelper.dWin, w);
        contentValues1.put(DbHelper.dDraw, d);
        contentValues1.put(DbHelper.dLoss, l);
        contentValues1.put(DbHelper.dGoalFOr, gf);
        contentValues1.put(DbHelper.dGoalAgainst, ga);
        contentValues1.put(DbHelper.dGoalDiff, gd);
        contentValues1.put(DbHelper.dPoints, pts);

        long id1 = sqLiteDatabase.insert(DbHelper.TABLE_NAME_1, null, contentValues1);
        return id1;
    }*/