package com.mlbench.probau.utils

import com.mlbench.tesla.utils.Constants
import io.paperdb.Paper

fun addToken(user_token: String?) {
    Paper.book(Constants.TOKEN).write("token", user_token)
}


fun getToken(): String? {
    val keys = Paper.book(Constants.TOKEN).allKeys
    if (keys.size == 0) return ""
    return Paper.book(Constants.TOKEN).read("token")
}

fun setUserType(userType: String?) {
    Paper.book(Constants.USER_TYPE).write("user_type", userType)
}


fun getUserType(): String? {
    val keys = Paper.book(Constants.USER_TYPE).allKeys
    if (keys.size == 0) return ""
    return Paper.book(Constants.USER_TYPE).read("user_type")
}

fun deleteToken(){
    Paper.book(Constants.TOKEN).delete("token")
}

/*
fun addCompanyFilter(company_filter: ArrayList<CompanyProjectFilterModel>) {
    Paper.book(Constants.COMPANY_FILTER).write("company_filter", company_filter)
}

fun getCompanyFilter(): ArrayList<CompanyProjectFilterModel> {
    val keys = Paper.book(Constants.COMPANY_FILTER).allKeys
    if (keys.size == 0) return ArrayList()
    return Paper.book(Constants.COMPANY_FILTER).read("company_filter")
}

fun addProjectFilter(project_filter: ArrayList<CompanyProjectFilterModel>) {
    Paper.book(Constants.PROJECT_FILTER).write("project_filter", project_filter)
}

fun getProjectFilter(): ArrayList<CompanyProjectFilterModel> {
    val keys = Paper.book(Constants.PROJECT_FILTER).allKeys
    if (keys.size == 0) return ArrayList()
    return Paper.book(Constants.PROJECT_FILTER).read("project_filter")
}

fun setSelectedLanguage(lang: String) {
    Paper.book(SELECT_LANGUAGE).write("selected_lang", lang)
}


fun getSelectedLanguage(): String {
    val keys =
        Paper.book(SELECT_LANGUAGE).allKeys
    if (keys.size == 0) return "en"
    return Paper.book(SELECT_LANGUAGE)?.read("selected_lang")!!
}

fun addDraftProject(draft_project: ArrayList<CreateProjectRequestModel>) {
    Paper.book(Constants.DRAFT_PROJECTS).write("draft_project", draft_project)
}

fun getDraftProject(): ArrayList<CreateProjectRequestModel> {
    val keys = Paper.book(Constants.DRAFT_PROJECTS).allKeys
    if (keys.size == 0) return ArrayList()
    return Paper.book(Constants.DRAFT_PROJECTS).read("draft_project")
}


fun deletePaperDbData(){

    addCompanyFilter(ArrayList<CompanyProjectFilterModel>())
    addProjectFilter(ArrayList<CompanyProjectFilterModel>())
    setUserType("")
    addDraftProject(ArrayList<CreateProjectRequestModel>())

}*/
