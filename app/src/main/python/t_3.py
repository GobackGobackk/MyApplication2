from firebase import firebase
import math
import random
import statistics

key="gheMMEEzZIQTWaVom8odgWFjvVM49TAVeKR59EFm"
authentication = firebase.FirebaseAuthentication(key, 'renkai284@gmail.com')#進行身分驗證，需要資料庫密鑰和Google資料庫擁有者帳號
firebase.authentication = authentication #身分驗證
user = authentication.get_user() #獲取使用者資訊
firebase = firebase.FirebaseApplication('https://hello-1647e-default-rtdb.firebaseio.com/', authentication=authentication) #登入資料庫

userId = '' # '使用者'ID
user_group = 0
group = []#放ID
satistic = {} #統計

re_satistic = {} #第二次循環
haverun = []
number = 0
AllGroup = []

sin_72 = 0.95105651629
cos_72 = 0.30901699437

AllProfile = firebase.get('/chatRooms/userProfiles',None) #type/:dict all userProfile
userProfile = None #firebase.get('/chatRooms/userProfiles',userId) # '當局者'檔案

#象限
one = {}
two = {}
three = {}
four = {}
user_coordinate = {} #’當局者‘座標

# 寫入資料庫
def write_group(userId,grpId):
    ref = '/chatRooms/group/'+grpId
    # group = group_two_s(userId)# 角度配對法統計
    group = group_two(userId)# 角度配對法無統計
    # group = group_one(userId) # 配對法一(satistic_for_dealer)無統計
    # group = group_one_s(userId)#配對法一(satistic_for_dealer)統計

    for i in group:
        print(AllProfile.get(i).get('goal'))
    firebase.put(ref,'members',group)

def dealwithAllProfile():
    global AllProfile
    friend = set()
    ProfileId = set()
    ProfileId.add(userId)
    if userProfile.get('fbFriends'):
        friend = set()
        for i in userProfile.get('fbFriends'):
            friend.add(i.get('facebookId'))
        for i in list(AllProfile):
            if AllProfile.get(i).get('fbId') in friend:
                ProfileId.add(i)
    if userProfile.get('friends'):
        friend = set()
        for i in userProfile.get('friends'):
            friend.add(userProfile.get('friends').get(i).get('userId'))
        for i in list(AllProfile):
            if i in friend:
                ProfileId.add(i)
    if userProfile.get('friends') == None and userProfile.get('fbFriends') == None:
        print('No any friends.')
    else :
        if (len(ProfileId)>=15):
            for i in list(AllProfile):
                if i not in ProfileId:
                    del AllProfile[i]




#goal篩選：
# 1. 選取與使用者有重疊目標（只要有重疊就行）的人
def goal_similar():
    # global userProfile
    # userProfile = firebase.get('/chatRooms/userProfiles', userId)
    user_goal = set() #<class 'set'>
    similar_goal_user = {}
    if userProfile.get('goal'):
        for i in userProfile.get('goal'):
            user_goal.add(userProfile.get('goal').get(i).get('goal'))
        print(user_goal)
        for i in AllProfile:
            if AllProfile.get(i).get('goal'):
                for j in AllProfile.get(i).get('goal'):
                    if AllProfile.get(i).get('goal').get(j).get('goal') in user_goal:
                        similar_goal_user[i] = {}
        users_similar_goals(similar_goal_user)
        for i in similar_goal_user:
            print(similar_goal_user.get(i))
        j_score(user_goal, similar_goal_user)
        get_goal_set(similar_goal_user)
        # caculate_goalscore(similar_goal_user)
    else:
        print('not any goal.')
    return similar_goal_user

#2. 把users這個字典的goal補上
def users_similar_goals(users):
    for i in list(users):
        users[i]['goal'] = set()
        for j in AllProfile.get(i).get('goal'):
            users[i]['goal'].add(AllProfile.get(i).get('goal').get(j).get('goal'))


#3.另法
def j_score(user_goal,users):
    print(len(users))
    print()
    for i in list(users):
        j_score = len(users.get(i).get('goal').intersection(user_goal))/len(users.get(i).get('goal').union(user_goal))
        users[i]['j'] = j_score
        if users[i]['j']<0.1:
            del users[i]
        else:
            print(users[i])
    print(len(users))


#3. 計算各個目標的出現次數當作權重分數(para:set)
def goalscore(users):
    goaldict = {}
    for i in users:
        for j in users.get(i).get('goal'):
            if goaldict.get(j) :
                goaldict[j] = goaldict[j] + 1
            elif goaldict.get(j) == None:
                goaldict[j] = 1
    for i in goaldict:
        print(i, goaldict.get(i))
    return goaldict
# 4. 計算goal分數（include 3.）
def caculate_goalscore(users):
    goaldict = goalscore(users)
    for i in list(users):
        goal_score = 0
        goal_number = 0
        for j in list(users.get(i).get('goal')):
            goal_score = goaldict.get(j) + goal_score
            goal_number += 1
        # j_score = len(users.get(i).get('goal').intersection(users.get(userId).get('goal')))/len(users.get(i).get('goal').union(users.get(userId).get('goal')))
        users[i]['score'] = (goal_score/goal_number) # *j_score
    for i in users:
        print(i)
        print(users.get(i).get('goal'))
        print(users.get(i).get('score'))

#4.另法
def get_goal_set(users):
    goaldict = goalscore(users)
    goalset = set()
    max_num_goal = max(goaldict, key = goaldict.get)
    std = statistics.stdev(list(goaldict.values()))#標準差
    mean_num = statistics.mean(list(goaldict.values()))
    print(std,mean_num)
    for i in goaldict:
        if goaldict.get(i) > mean_num+1*std:
            print(i)
            goalset.add(i)
    for i in list(users):
        j_score = len(users.get(i).get('goal').intersection(goalset))/len(users.get(i).get('goal').union(goalset))
        if j_score == 0:
            del users[i]
        else:
            users[i]['j_score'] = j_score
            print(users.get(i))
    print(len(users))

#檢測四個象限是否為空
def detect_c():
    if one and two and three and four:
        return
    num1 = 0
    num2 = 0
    num3 = 0
    num4 = 0
    if one == None:
        num1 = 1
    if two == None:
        num2 = 1
    if three == None:
        num3 = 1
    if four == None:
        num4 = 1

    APro = firebase.get('/chatRooms/userProfiles',None)
    for i in APro:
        if APro.get(i).get('active'):
            x = APro.get(i).get('active').get('O')-APro.get(i).get('active').get('G')
            y = APro.get(i).get('active').get('D')-APro.get(i).get('active').get('I')
            if x > 0:
                if y > 0:
                    if num1 :
                        one[id] = {'x': x, 'y': y}
                else:
                    if num4 :
                        four[id] = {'x': x, 'y': y}
            else:
                if y > 0:
                    if num2 :
                        two[id] = {'x': x, 'y': y}
                else:
                    if num3 :
                        three[id] = {'x': x, 'y': y}



# 抓取使用者ID，帶入此檔案的userId
def get_user(ID):
    global userId
    userId = ID

# 計算輸入profile的x,y，並存於=>one,two,three,four(包含使用者、當局者)
# (dict:/chatRooms/userProfiles/targetfile)
def c_ogdi(targetfile):
   # print('c_ogdi(',uId,', targetfile)')
   id = targetfile.get('id')
   x = targetfile.get('active').get('O')-targetfile.get('active').get('G')
   y = targetfile.get('active').get('D')-targetfile.get('active').get('I')
   if x > 0:
      if y > 0:
         one[id] = {'x': x,'y': y}
      else :
         four[id] = {'x': x, 'y': y}
   else :
      if y > 0:
         two[id] = {'x': x,'y': y}
      else :
         three[id] = {'x': x, 'y': y}


# 計算‘當局者‘的x,y，存於=>user_coordinate
# (當局者(uId),targetfile(userProfile))
def u_ogdi(uId, targetfile):
    id = targetfile.get('id')
    x = targetfile.get('active').get('O') - targetfile.get('active').get('G')
    y = targetfile.get('active').get('D') - targetfile.get('active').get('I')
    if id == uId:
        user_coordinate[id] = {'x': x, 'y': y}
        return

# 給定一個座標，判斷要用哪個象限計算最小距離(int, int)
def callFunction(x,y):
   # print('callFunction')
   if x > 0 :
      if y > 0:
         #find one
         return find_closer(x, y, one)
      else :
         #find four
         return find_closer(x, y, four)
   else :
      if y > 0:
         #find two
         return find_closer(x, y, two)
      else :
         #find three
         return find_closer(x, y, three)

# 給定一個座標，計算最小距離
def find_closer(x, y, quadrant): #int, int, dict
   #print('find_closer: ',x,' ',y)
   # print('find_closer')
   id = None
   min = math.pow(math.pow(28, 2) + math.pow(28, 2), 0.5)
   for q in list(quadrant):
      q_x = quadrant[q].get('x')
      q_y = quadrant[q].get('y')
      distance = math.pow(math.pow(q_x - x, 2) + math.pow(q_y - y, 2), 0.5)
      if distance < min and( q not in group):
         id = q
         # print(distance, min, id)
         min = distance
   #print('idx and idy:',quadrant[id].get('x'), '  ',quadrant[id].get('y'))
   return id

# 判定一個列表中是否含有’使用者‘，若有就刪除之，並計算包含‘使用者’的群組個數，若有回傳True，反之回傳False
# (list)
def determine(listGroup):
    global number
    if userId in listGroup:
        listGroup.remove(userId)
        number += 1
        return True
    return False

# 存入satistic=>{'displayName','userId','frequency'}，並計算此人出現次數，存入frequency
# (list, dict{"displayName","userId","frequency"})
def satistic_F(group, satistic):
    for m in group:
        if satistic.get(m) == None:
            satistic[m] = {"displayName": AllProfile[m].get('name'), "userId": m, "frequency": 1}
        else:
            satistic[m]['frequency'] += 1

# 判定一個列表中是否含有’當局者‘，並計算包含‘當局者’的群組個數，若有回傳True，反之回傳False
# (list)
def re_determine(uId, listGroup):
    global number
    if uId in listGroup:
        listGroup.remove(uId)
        number += 1
        print(True)
        return True
    return False

# 存入satistic=>{'displayName','userId','frequency'}，並計算此人出現次數，存入frequency
# (list, dict{"displayName","userId","frequency"})
def re_satistic_F(user, group, re_satistic, uId):
    if uId in group:
        print(True)
        if re_satistic.get(user) == None:
            re_satistic[user] = {"displayName": AllProfile[user].get('name'), "userId": user, "frequency": 1}
        else:
            re_satistic[user]['frequency'] += 1
    else:
        print(False)
# 把座標加起來（要被加的x, 要被加的y, Id）=> int,int,str
def addUp(x, y, id):
    if one.get(id):
        x = x + one.get(id).get('x')
        y = y + one.get(id).get('y')
    elif two.get(id):
        x = x + two.get(id).get('x')
        y = y + two.get(id).get('y')
    elif three.get(id):
        x = x + three.get(id).get('x')
        y = y + three.get(id).get('y')
    elif four.get(id):
        x = x + four.get(id).get('x')
        y = y + four.get(id).get('y')
    return x, y

# 清空資料，初始化
def cleardata(uId):
    group.clear()
    user_coordinate.clear()
    global userProfile
    userProfile = firebase.get('/chatRooms/userProfiles', uId)

# 找與‘當局者’匹配之組員，並記錄在satistic
def satistic_for_dealer(uId):

    u_ogdi(uId, userProfile) # 把'當局者'的座標存入user_coordinate
    print(user_coordinate)
    x = user_coordinate.get(uId).get('x')
    y = user_coordinate.get(uId).get('y')

    group.append(callFunction(-x, y))
    group.append(callFunction(-x, -y))
    group.append(callFunction(x, -y))

    for i in group:
        x, y = addUp(x, y, i)

    group.append(callFunction(-x, -y))
    group.append(uId)
    return group

# 如果傳入的列表(group)包含uId，就會加入satistic裡面並計算frequency
def apperance_group(uId,group):
    if uId in group:
        print(group)
        for i in group:
            if satistic.get(i) == None:
                satistic[i] = {"displayName": AllProfile.get(i).get('name'), "userId": i, "frequency": 1}
            elif satistic.get(i):
                satistic[i]['frequency'] += 1

# 把satis_dict裡每筆frequency都除以uId的出現次數(就是變成機率形式)，uId通常是userId或是跟apperance_group的uId一樣
def caculate_frequency(uId, satis_dict):
    x = satis_dict.get(uId).get('frequency')
    for i in satis_dict:
        satis_dict[i]['frequency'] = satis_dict.get(i).get('frequency') / x
    return satis_dict.copy()

def get_five_member(satistic): # 從satistic中找5個成員
    # print('get_five_member:',satistic,'\n')
    #
    # # 法ㄧ： 挑選frequency最大的五人為一組
    # index_s = []
    # freq = []
    # for i in satistic:
    #     if len(index_s) < 5:
    #         index_s.append(satistic.get(i).get('userId'))
    #         freq.append(satistic.get(i).get('frequency'))
    #     elif len(index_s) == 5:
    #         min_feq = min(freq)
    #         if satistic.get(i).get('frequency') >= min_feq :
    #
    #             del index_s[freq.index(min_feq)]
    #             freq.remove(min_feq)
    #             index_s.append(satistic.get(i).get('userId'))
    #             freq.append(satistic.get(i).get('frequency'))
    # return index_s
    # =============================分隔線==================================
    # 法二： 挑選frequency最大的四人為一組，但每個象限至少一人。userId不加入比較迴圈，事先包含在組裡
    index_s = [None, None, None, None, None] #index=>象限
    quadrant = [ None, 'one','two','three','four']  # index=>象限
    freq = [-1, -1, -1, -1, -1] #index=>象限

    for i in satistic:  # 1~4象限各選一個最大的
        i_index = quadrant.index(one_two_three_four(i)) # i_index代表第幾象限
        if satistic.get(i).get('frequency') >= freq[i_index] :
            freq[i_index] = satistic.get(i).get('frequency')
            index_s[i_index] = satistic.get(i).get('userId')

    for i in satistic:
        if i in index_s: continue
        elif i not in index_s:
            i_freq = satistic.get(i).get('frequency')
            if i_freq >= freq[0]:
                freq[0] = i_freq
                index_s[0] = i
    print('index_s:',index_s,'\n')

    return index_s

def one_two_three_four(id):
    if id in one:
        return 'one'
    elif id in two:
        return 'two'
    elif id in three:
        return 'three'
    elif id in four:
        return 'four'

def degree(uId): # 找出除了uId外的四個最佳座標，並以dict回傳
    x = user_coordinate.get(uId).get('x')
    y = user_coordinate.get(uId).get('y')
    r = math.pow(math.pow(x,2) + math.pow(y,2), 0.5)

    sin_user = y/r
    cos_user = x/r

    cos_plus = cos_72 * cos_user - sin_72 * sin_user # cos(α + β) = cos(α)cos(β) – sin(α)sin(β)
    sin_plus = sin_72 * cos_user + cos_72 * sin_user #sin(α + β) = sin(α)cos(β) + cos(α)sin(β)

    player = {}
    for i in range(4):
        p_x = cos_plus * r
        p_y = sin_plus * r
        player[i] = {'x': p_x, 'y': p_y}
        cos_t = cos_plus
        sin_t = sin_plus
        cos_plus = cos_72 * cos_t - sin_72 * sin_t  # cos(α + β) = cos(α)cos(β) – sin(α)sin(β)
        sin_plus = sin_72 * cos_t + cos_72 * sin_t  # sin(α + β) = sin(α)cos(β) + cos(α)sin(β)
    return player

#========================================
# 配對法一(satistic_for_dealer)統計：
def group_one_s(userId):
    global satistic,group
    print('\nstart')
    for i in AllProfile:
        cleardata(i)
        g = satistic_for_dealer(i).copy()
        AllGroup.append(g)
        apperance_group(userId, g)
    print('start')
    for i in AllGroup:
        print(i)
    print('end\n')
    satistic = caculate_frequency(userId,satistic)
    print()
    print('start')
    for i in satistic:
        print(satistic.get(i))
    print('end')
    group.clear()
    group = get_five_member(satistic)

    print()
    for i in group:
        del satistic[i]['frequency']
        print(one_two_three_four(i), satistic.get(i))
    userN = []
    for i in group:
        userN.append(satistic.get(i).get('displayName'))
    print(userN)
    return satistic

#=======================================================
# 配對法一(satistic_for_dealer)無統計：
def group_one(userId):
    global satistic
    print('Sa',satistic)
    cleardata(userId)
    get_user(userId)
    group = satistic_for_dealer(userId)
    print('sa', satistic)
    satistic_F(group, satistic)
    userN = []
    for i in group:
        del satistic[i]['frequency']
        userN.append(AllProfile.get(i).get('name'))
    print(group)
    print(userN)
    return satistic

#=======================================================
# 角度配對法 無統計：
def group_two(userId):
    cleardata(userId)
    u_ogdi(userId, userProfile)
    player = degree(userId)
    for i in player:
        x = player.get(i).get('x')
        y = player.get(i).get('y')
        group.append(callFunction(x, y))

    group.append(userId)
    for i in group:
        print(one_two_three_four(i), i)
    global satistic
    satistic_F(group, satistic)
    userN = []
    for i in group:
        del satistic[i]['frequency']
        userN.append(satistic.get(i).get('displayName'))
    print(userN)
    return satistic

#=======================================================
# 角度配對法統計：
def group_two_s(userId):

    global group, satistic
    for i in AllProfile:
        cleardata(i)
        u_ogdi(i, userProfile)
        player = degree(i)
        for j in player:
            x = player.get(j).get('x')
            y = player.get(j).get('y')
            group.append(callFunction(x, y))
        group.append(i)
        g = group.copy()
        AllGroup.append(g)
        apperance_group(userId, g)
    satistic = caculate_frequency(userId,satistic)
    for i in satistic:
        print(one_two_three_four(i), satistic.get(i))
    group.clear()
    group = get_five_member(satistic)

    for i in group:
        print(one_two_three_four(i), satistic.get(i))
        if satistic.get(i):
            if satistic.get(i).get('frequency'):
                del satistic[i]['frequency']
        # print(one_two_three_four(i), satistic.get(i))

    for i in list(satistic):
        if satistic.get(i).get('frequency') != None:
            del satistic[i]
    userN = []
    print('group:',group)
    for i in group:
        userN.append(satistic.get(i).get('displayName'))
    print(userN)
    print(satistic)
    return satistic

def classify_data(filter):
    for i in list(AllProfile):
        if i == userId:
            continue
        if i not in filter:
            del AllProfile[i]
        else:
            print(i)
            c_ogdi(AllProfile.get(i))
    print('分類成功')
    print(one)
    print(two)
    print(three)
    print(four)

def main(userId,grpId):
    global userProfile
    userProfile = firebase.get('/chatRooms/userProfiles', userId)
    get_user(userId)
    # dealwithAllProfile()
    detect_c()
    filter = goal_similar()
    classify_data(filter)
    write_group(userId, grpId)

#===============start====================

# main('NU4CPYyA0eW3WtSg2G9UC2OcU643','ttttt')






#=========================================================
# # 配對法一(satistic_for_dealer)進階統計：
# print('\nstart')
# for i in AllProfile:
#     cleardata(i)
#     g = satistic_for_dealer(i).copy()
#     AllGroup.append(g)
#     apperance_group(userId, g)
# print('start')
# for i in AllGroup:
#     print(i)
# print('end\n')
# satistic = caculate_frequency(userId,satistic)
# print()
# print('start')
# for i in satistic:
#     print(satistic.get(i))
# print('end')
# group.clear()
# group = get_five_member(satistic)
#
# print()
# for i in group:
#     print(one_two_three_four(i), satistic.get(i))
# userN = []
# for i in group:
#     userN.append(satistic.get(i).get('displayName'))
# print(userN)
