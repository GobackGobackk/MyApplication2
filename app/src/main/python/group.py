from firebase import firebase

key="gheMMEEzZIQTWaVom8odgWFjvVM49TAVeKR59EFm"
authentication = firebase.FirebaseAuthentication(key, 'renkai284@gmail.com')#進行身分驗證，需要資料庫密鑰和Google資料庫擁有者帳號
firebase.authentication = authentication #身分驗證
user = authentication.get_user() #獲取使用者資訊
firebase = firebase.FirebaseApplication('https://hello-1647e-default-rtdb.firebaseio.com/', authentication=authentication) #登入資料庫

goals=[] #存取使用者目標

def write_group(userId,grpId):
    ref = '/chatRooms/group/'+grpId
    group = group_g(userId)
    # print(type(ref))
    # ref1 = json.dumps(ref)
    # print(type(ref1))
    # firebase.post(ref,group)
    firebase.put(ref,'members',group)


def collect_goals(targetgoals):
    return_goals = []
    for goalId in targetgoals:
        return_goals.append(targetgoals[goalId].get('goal'))
    return return_goals

def group_g(userId):
    ref = '/chatRooms/userProfiles/'+userId+'/goal'
    user_g = firebase.get(ref,None)
    goals = collect_goals(user_g)
    print(goals)
    comparision = {}#空dict
    same_array = []

    ref = '/chatRooms/userProfiles/'+userId
    un = firebase.get(ref, 'name')
    comparision[len(comparision)] = {"displayName": un, "userId": userId}
    same_array.append(1)

    ref = '/chatRooms/userProfiles/'
    AllProfile = firebase.get(ref,None)
    for p in AllProfile:
        if (p == userId): continue
        print(AllProfile[p].get('id'))
        user_goals = AllProfile[p].get('goal')
        u_goal = collect_goals(user_goals)

        same = set(goals)&set(u_goal)
        same_n = (len(same)*len(same))/(len(u_goal)*len(goals))#匹配分數
        print(same,'\n')
        if(len(comparision) < 5):
            comparision[len(comparision)] = {"displayName":AllProfile[p].get('name'), "userId":p}
            same_array.append(same_n)
        else:
            if(len(same) > max(same_array) or len(same) > min(same_array)):
                min_index = same_array.index(min(same_array))
                del comparision[min_index]
                del same_array[min_index]
                comparision[min_index] = {"displayName":AllProfile[p].get('name'), "userId":p}
                same_array.insert(min_index,same_n)
    for k in list(comparision.keys()):
        Id = comparision[k].get('userId')
        comparision[Id] = comparision.pop(k)
    print(comparision)
    return comparision