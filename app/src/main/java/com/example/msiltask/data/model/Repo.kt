package com.example.msiltask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class RepoResponse(
    @Expose
    @SerializedName("total_count")
    val totalCount: Int,
    @Expose
    @SerializedName("incomplete_results")
    val incompleteResult: Boolean,
    @Expose
    val items: List<RepoItem>
)

data class RepoItem(
    @Expose
    val id: Int,
    @Expose
    @SerializedName("node_id")
    val nodeId: String,
    @Expose
    val name: String,
    @Expose
    @SerializedName("full_name")
    val fullName: String,
    @Expose
    @SerializedName("private")
    val privateState: Boolean,
    @Expose
    val owner: Owner,

    @Expose
    @SerializedName("html_url")
    val htmlUrl: String,

    @Expose
    val description: String,

    @Expose
    val fork: Boolean,

    @Expose
    val url: String,

    @Expose
    @SerializedName("forks_url")
    val forksUrl: String,

    @Expose
    @SerializedName("keys_url")
    val keysUrl: String,

    @Expose
    @SerializedName("collaborators_url")
    val collaboratorsUrl: String,

    @Expose
    @SerializedName("teams_url")
    val teamsUrl: String,

    @Expose
    @SerializedName("hooks_url")
    val hooksUrl: String,

    @Expose
    @SerializedName("issue_events_url")
    val issueEventsUrl: String,

    @Expose
    @SerializedName("events_url")
    val eventsUrl: String,

    @Expose
    @SerializedName("assignees_url")
    val assigneesUrl: String,

    @Expose
    @SerializedName("branches_url")
    val branchesUrl: String,

    @Expose
    @SerializedName("tags_url")
    val tagsUrl: String,

    @Expose
    @SerializedName("blobs_url")
    val blobsUrl: String,

    @Expose
    @SerializedName("git_tags_url")
    val gitTagsUrl: String,

    @Expose
    @SerializedName("git_refs_url")
    val gitRefsUrl: String,

    @Expose
    @SerializedName("trees_url")
    val treesUrl: String,

    @Expose
    @SerializedName("statuses_url")
    val statusesUrl: String,

    @Expose
    @SerializedName("languages_url")
    val languagesUrl: String,

    @Expose
    @SerializedName("stargazers_url")
    val stargazersUrl: String,

    @Expose
    @SerializedName("contributors_url")
    val contributorsUrl: String,

    @Expose
    @SerializedName("subscribers_url")
    val subscribersUrl: String,

    @Expose
    @SerializedName("subscription_url")
    val subscriptionUrl: String,

    @Expose
    @SerializedName("commits_url")
    val commitsUrl: String,

    @Expose
    @SerializedName("git_commits_url")
    val gitCommitsUrl: String,

    @Expose
    @SerializedName("comments_url")
    val commentsUrl: String,

    @Expose
    @SerializedName("issue_comment_url")
    val issueCommentUrl: String,

    @Expose
    @SerializedName("contents_url")
    val contentsUrl: String,

    @Expose
    @SerializedName("compare_url")
    val compareUrl: String,

    @Expose
    @SerializedName("merges_url")
    val mergesUrl: String,

    @Expose
    @SerializedName("archive_url")
    val archiveUrl: String,

    @Expose
    @SerializedName("downloads_url")
    val downloadsUrl: String,

    @Expose
    @SerializedName("issues_url")
    val issuesUrl: String,

    @Expose
    @SerializedName("pulls_url")
    val pullsUrl: String,

    @Expose
    @SerializedName("milestones_url")
    val milestonesUrl: String,

    @Expose
    @SerializedName("notifications_url")
    val notificationsUrl: String,

    @Expose
    @SerializedName("labels_url")
    val labelsUrl: String,

    @Expose
    @SerializedName("releases_url")
    val releasesUrl: String,

    @Expose
    @SerializedName("deployments_url")
    val deploymentsUrl: String,

    @Expose
    @SerializedName("created_at")
    val createdAt: String,

    @Expose
    @SerializedName("updated_at")
    val updatedAt: String,

    @Expose
    @SerializedName("pushed_at")
    val pushedAt: String,

    @Expose
    @SerializedName("git_url")
    val gitUrl: String,

    @Expose
    @SerializedName("ssh_url")
    val sshUrl: String,

    @Expose
    @SerializedName("clone_url")
    val cloneUrl: String,

    @Expose
    @SerializedName("svn_url")
    val svnUrl: String,

    @Expose
    val homepage: String,

    @Expose
    val size: Int,

    @Expose
    @SerializedName("stargazers_count")
    val stargazersCount: Int,

    @Expose
    @SerializedName("watchers_count")
    val watchersCount: Int,

    @Expose
    val language: String,

    @Expose
    @SerializedName("has_issues")
    val hasIssues: Boolean,

    @Expose
    @SerializedName("has_projects")
    val hasProjects: Boolean,

    @Expose
    @SerializedName("has_downloads")
    val hasDownloads: Boolean,

    @Expose
    @SerializedName("has_wiki")
    val hasWiki: Boolean,

    @Expose
    @SerializedName("has_pages")
    val hasPages: Boolean,

    @Expose
    @SerializedName("forks_count")
    val forksCount: Int,

    @Expose
    val archived: Boolean,

    @Expose
    val disabled: Boolean,

    @Expose
    @SerializedName("open_issues_count")
    val openIssuesCount: Int,

    @Expose
    val forks: Int,

    @Expose
    @SerializedName("open_issues")
    val openIssues: Int,

    @Expose
    val watchers: Int,

    @Expose
    @SerializedName("default_branch")
    val defaultBranch: String,

    @Expose
    val score: Double
):Serializable

data class Owner(
    @Expose
    val login: String,
    @Expose
    val id: Int,
    @Expose
    @SerializedName("node_id")
    val nodeId: String,
    @Expose
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @Expose
    @SerializedName("gravatar_id")
    val gravatarId: String,
    @Expose
    val url: String,
    @Expose
    @SerializedName("html_url")
    val htmlUrl: String,
    @Expose
    @SerializedName("followers_url")
    val followersUrl: String,
    @Expose
    @SerializedName("following_url")
    val followingUrl: String,
    @Expose
    @SerializedName("gists_url")
    val gistsUrl: String,
    @Expose
    @SerializedName("starred_url")
    val starredUrl: String,
    @Expose
    @SerializedName("subscriptions_url")
    val subscriptionsUrl: String,
    @Expose
    @SerializedName("organizations_url")
    val organizationsUrl: String,
    @Expose
    @SerializedName("repos_url")
    val reposUrl: String,
    @Expose
    @SerializedName("events_url")
    val eventsUrl: String,
    @Expose
    @SerializedName("received_events_url")
    val receivedEventsUrl: String,
    @Expose
    val type: String,
    @Expose
    @SerializedName("site_admin")
    val siteAdmin: Boolean
):Serializable
