# 2024-08-07

How to set up ssh with github
``` 
Host work.github.com
  HostName ssh.github.com
  User angelswing
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/angelswing
  Port 443


Host home.github.com
  HostName ssh.github.com
  User home
  AddKeysToAgent yes
  UseKeychain yes
  IdentityFile ~/.ssh/home
  Port 443
```
[ssh setting method](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent)


